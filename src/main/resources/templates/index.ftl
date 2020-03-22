<html>
<head>
    <title></title>
    <script type='text/javascript' src='/dwr/engine.js'></script>
    <script type='text/javascript' src='/dwr/interface/DemoService.js'></script>
    <script type="text/javascript" src="../static/jquery-1.7.2.min.js"></script>

</head>
<input onclick="onclie1()" value="前后台联通测试" style="height: 20px;width: 200px;" type="button"></input>

<input onclick="onclie12()" value="导出转固工单excel" style="height: 20px;width: 200px;background-color: aquamarine" type="button"></input>

<input onclick="onclie123()" value="导出转固工单excel测试" style="height: 20px;width: 200px;background-color: aquamarine" type="button"></input>


<script>
   function onclie1 () {
       DemoService.echo('回声测试', function (str) {
           alert(str);
       });
   }


   function onclie12 () {
       DemoService.exportResourceInExcel('回声测试',
               {
                   callback: function (data) {
                       dwr.engine.openInDownload(data);
                   },
                   async: false
               }
       );
   }

   function onclie123() {
       DemoService.exportExcleResourceByParam('回声测试',
               {
                   callback: function (data) {
                       dwr.engine.openInDownload(data);
                   },
                   async: false
               }
       );
   }
</script>
</html>