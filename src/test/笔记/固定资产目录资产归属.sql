  WITH  table1 as (
               SELECT

      t.MDATA_ID                                               AS mdataId,
      t.CODE1 || t.CODE2 || t.CODE3 || t.CODE4 || t.ASCRIPTION AS combCode,
      t.JOBCOSTCLASS                                           AS jobcostclass
    FROM DE_OM_V_O_CJOBCOSTCLASS t
      JOIN DM_OMDATA t2
        ON t.MDATA_ID = t2.ID
    WHERE t2.IS_VALID = 100383
         and  t.ASCRIPTION='A003'
) select *  from  table1  t1  where  t1.combCode='05060205A003';---固定资产目录拼接资产归属的