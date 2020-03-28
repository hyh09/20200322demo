with table_A AS (
    SELECT
      t.MDATA_ID                                               AS mdataId,
      t.CODE1 || t.CODE2 || t.CODE3 || t.CODE4 || t.ASCRIPTION AS combCode,
      t.JOBCOSTCLASS                                           AS jobcostclass,
      t.ASCRIPTION                                             AS order4,
      t.CODE1 || t.CODE2 || t.CODE3 || t.CODE4                 AS zzk1
    FROM DE_OM_V_O_CJOBCOSTCLASS t
      JOIN DM_OMDATA t2
        ON t.MDATA_ID = t2.ID
    WHERE t2.IS_VALID = 100383
)select  tt.num 带出的条数,
         ta.jobcostclass  作业资产类别,
         ta.order4  资产归属,
         (select  t.ZZCLB_DEC  from DE_OM_V_O_ZTZCLB_T t where t.ZZ_ZCLB=ta.jobcostclass) 作业资产类别中文名,
         ta.zzk1  资产目录
 from    table_A ta ,table_count  tt
 where  tt.COMBCODE=ta.combCode
        -- and  ta.zzk1='03010208'---固定资产目录--
        and ta.order4='A003'---资产归属
        and tt.NUM>1;--->1 就是带出多条






SELECT
  t.MDATA_ID as mdataId,
  t.ZZ_ZCLB as zzZclb,
  t.ZZCLB_DEC as zzclbDec
FROM DE_OM_V_O_ZTZCLB_T t
JOIN DM_OMDATA t2
on t.MDATA_ID = t2.ID
where t2.IS_VALID = 100383
and  t.ZZCLB_DEC='待指定';


select IS_VALID  from  DM_OMDATA  td  where  td.id='100000100000000001436460';

update  DM_OMDATA  td set td.IS_VALID='100382' where  td.id='100000100000000001436460';


select *  from  DM_STAFF  dm   where  dm.ACCOUNT='45000644@GX';










select *  from  PM_MONITOR_SYS_DICT  pt  where  pt.THE_KEY='ZZ_KEY_REMOVE';

select *
FROM MM_ASSET_SPEC t
where t.IS_VALID=1001
and length(t.CODE)<8;
















--000000776197
SELECT * FROM (SELECT tt.*, ROWNUM AS rowno_ FROM
  (select eo.id ,eo.code,eo.status_id,to_char(eo.create_time,'yyyy-MM-dd HH24:mm:ss') as createTime,
     eo.DISPATCH_OPER_ID, eo.deal_oper_id ,ee.bukrs,eo.type_id, eo.RECEIVE_OPER_ID, eo.SPEC_ID,
     ee.assets_card_code
   from em_orderitem em join ee_orderitem_asset ee on em.id=ee.orderitem_id
     join em_order eo on eo.id=em.order_id and eo.status_id not in (1000006, 1000019)
                         and eo.type_id not in (1001380, 1001382, 1001384)
   WHERE  eo.DEAL_OPER_ID is not null
     --ee.assets_card_code = '000000776197' and ee.bukrs = 'A020'
   union
   select eo.id ,eo.code,eo.status_id,to_char(eo.create_time,'yyyy-MM-dd HH24:mm:ss') as createTime,
     eo.DISPATCH_OPER_ID, eo.deal_oper_id ,ee.bukrs,eo.type_id,
     eo.RECEIVE_OPER_ID, eo.SPEC_ID
   from em_orderitem em join ee_orderitem_asset ee on em.id=ee.orderitem_id
     join em_order eo on eo.id=em.order_id and eo.status_id not in (1000006, 1000019) and eo.spec_id = 3010100009
WHERE
  --ee.assets_card_code = '000000776197' and ee.bukrs = 'A020'
  )
                                                  tt WHERE ROWNUM <= 10) tbl WHERE tbl.rowno_ > 0


---000001788709
---9eb133f6a3921b1af47eedb7ba87c86a
select *  from DM_STAFF  dm  where  dm.ACCOUNT='45000644@GX';---Xbtx_wsx528   9eb133f6a3921b1af47eedb7ba87c86a


select *  from  DM_STAFF  dm   where  dm.ACCOUNT='45140137@GX';
update   DM_STAFF   dm  set  dm.PASSWORD='9eb133f6a3921b1af47eedb7ba87c86a' where  dm.ACCOUNT='45340165@GX';







