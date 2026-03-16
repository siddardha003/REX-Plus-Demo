-- View 1: beneficiary_to_platform_current
DROP VIEW IF EXISTS rexbase.beneficiary_to_platform_current CASCADE^;
CREATE OR REPLACE VIEW rexbase.beneficiary_to_platform_current AS
SELECT
    plt.id AS platform_id,
    ben.id AS beneficiary_id,
    plt.displayname AS platform,
    emp.displayname AS employer,
    ben.listname,
    ben.leavingdate,
    borg.personnelnumbersystem,
    borg.personnelnumberpayroll,
    borg.identity_tag,
    borg.companysite,
    borg.orgunit,
    borg.costcenter
FROM rexbase.beneficiary ben
JOIN rexbase.beneficiary_orgdata borg ON borg.beneficiary_id = ben.id
JOIN rexbase.employer emp ON emp.id = borg.employer_id
JOIN rexbase.platform plt ON plt.id = emp.platform_id
WHERE CURRENT_DATE >= borg.validfrom
  AND CURRENT_DATE <= COALESCE(borg.validthrough, '2999-12-31'::date)^;

ALTER VIEW rexbase.beneficiary_to_platform_current OWNER TO postgres^;
COMMENT ON VIEW rexbase.beneficiary_to_platform_current IS 'list of all beneficiaries per platform and their current organizational data'^;

-- View 2: picklist_members
DROP VIEW IF EXISTS rexbase.picklist_members CASCADE^;
CREATE OR REPLACE VIEW rexbase.picklist_members AS
WITH beneficiary_list AS (
    SELECT
        bl.picklist_id,
        'INTERNAL'::text AS member_type,
        ben.listname,
        emp.displayname AS companyname
    FROM rexbase.picklist_to_beneficiary bl
    JOIN rexbase.beneficiary ben ON ben.id = bl.beneficiary_id
    JOIN rexbase.beneficiary_orgdata borg ON borg.beneficiary_id = ben.id
    JOIN rexbase.employer emp ON emp.id = borg.employer_id
    WHERE CURRENT_DATE >= borg.validfrom
      AND CURRENT_DATE <= COALESCE(borg.validthrough, '2999-12-31'::date)
),
guest_list AS (
    SELECT
        gl.picklist_id,
        'EXTERNAL'::text AS member_type,
        gu.listname,
        gu.organization AS companyname
    FROM rexbase.picklist_to_guest gl
    JOIN rexbase.guest gu ON gu.id = gl.guest_id
)
SELECT * FROM beneficiary_list
UNION
SELECT * FROM guest_list^;

ALTER VIEW rexbase.picklist_members OWNER TO postgres^;
COMMENT ON VIEW rexbase.picklist_members IS 'list for retrieval of all members of a given picklist'^;

-- View 3: rexuser_rights (fixed drop logic)
DO '
BEGIN
    IF EXISTS (SELECT FROM pg_tables WHERE schemaname = ''rexbase'' AND tablename = ''rexuser_rights'') THEN
        DROP TABLE rexbase.rexuser_rights CASCADE;
    END IF;
    IF EXISTS (SELECT FROM pg_views WHERE schemaname = ''rexbase'' AND viewname = ''rexuser_rights'') THEN
        DROP VIEW rexbase.rexuser_rights CASCADE;
    END IF;
END;
' LANGUAGE plpgsql^;

CREATE OR REPLACE VIEW rexbase.rexuser_rights AS
WITH rightscomplete AS (
    SELECT
        rexusr.id AS rexuser_id,
        rexusr.listname,
        rexusr.activefrom AS user_activefrom,
        rexusr.activethrough AS user_activethrough,
        roles.activefrom AS role_activefrom,
        roles.activethrough AS role_activethrough,
        rights.activefrom AS right_activefrom,
        rights.activethrough AS right_activethrough,
        role_rights.activefrom AS roleright_activefrom,
        role_rights.activethrough AS roleright_activethrough,
        user_role.activefrom AS userrole_activefrom,
        user_role.activethrough AS userrole_activethrough,
        rexusr.active_range @> CURRENT_DATE AS user_active,
        roles.active_range @> CURRENT_DATE AS role_active,
        rights.active_range @> CURRENT_DATE AS right_active,
        role_rights.active_range @> CURRENT_DATE AS roleright_active,
        user_role.active_range @> CURRENT_DATE AS userrole_active,
        roles.rolename_de,
        roles.rolename_en,
        rights.rightname_de,
        rights.rightname_en,
        rights.rightcode
    FROM rexbase.rexuser rexusr
    JOIN rexbase.rexuser_to_role     user_role   ON user_role.rexuser_id   = rexusr.id
    JOIN rexbase.userrole_to_right   role_rights ON role_rights.userrole_id = user_role.userrole_id
    JOIN rexbase.userright           rights      ON rights.id              = role_rights.userright_id
    JOIN rexbase.userrole            roles       ON roles.id               = role_rights.userrole_id
)
SELECT DISTINCT
    rexuser_id,
    listname,
    rightcode
FROM rightscomplete
WHERE user_active
  AND role_active
  AND right_active
  AND roleright_active
  AND userrole_active^;

ALTER VIEW rexbase.rexuser_rights OWNER TO postgres^;
COMMENT ON VIEW rexbase.rexuser_rights IS 'list all effective rights of a particular user'^;
