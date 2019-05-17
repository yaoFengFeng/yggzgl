<?php
include ('head.php');
?>
</head>
<body>

<div class="layui-card-body">
    <div class="layui-upload">
        <button type="button" class="layui-btn layui-btn-normal" id="test-upload-change">选择文件</button>
        <button type="button" class="layui-btn" id="test-upload-change-action">开始导入</button>
        <p class="myFileName"></p>
        <input type="file" id="uploadFile" style="display:none" value="看不见我" onchange="importf(this)">
    </div>
</div>


<script src="<?=BASE_URL?>layuiadmin/xlsx.full.min.js"></script>
<script src="<?=BASE_URL?>layuiadmin/layui/layui.js"></script>
<script>


    layui.config({
        base: '<?=BASE_URL?>layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'table', 'layer', 'upload'], function () {
        var admin = layui.admin
            , $ = layui.jquery
            , table = layui.table
            , upload = layui.upload;

//         var layer = layui.layer;
//         //指定允许上传的文件类型
//         upload.render({
//             elem: '#test-upload-change'
//             ,url: '/upload/'
//             ,auto: false
//             //,multiple: true
//             ,bindAction: '#test-upload-change-action'
//             ,exts:'xlsx'
//             ,done: function(res){
//                 console.log(res)
//             }
//         });

        $('#test-upload-change').click(function () {
            $('#uploadFile').click();
        });

        $('#uploadFile').click(function () {
        });

        //点击开始导入
        $('#test-upload-change-action').click(function () {
            if(!wb){
                alert('请先选择数据表文件');
                return;
            }
            var data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
            /* data是从excel获取的数据*/
            var updata = [];

            /******
             * 将excel中的中文表头（data中的属性值）全部改成 与数据库表头一样的拼音表头 方便后续插入到数据库中
             *将data克隆到updata中
             ********/

            for (var a in data) {
                updata[a] = {};
                for (var key in data[a]) {
                    if (data[a].hasOwnProperty(key)) {
                        switch (key) {
                            case 'id':
                                updata[a].id = data[a][key];
                                break;
                            case '学年学期':
                                updata[a].xnxq = data[a][key];
                                break;
                            case '选课课号':
                                updata[a].xkkh = data[a][key];
                                break;
                            case '课程名称':
                                updata[a].kcmc = data[a][key];
                                break;
                            case '教师姓名':
                                updata[a].jsxm = data[a][key];
                                break;
                            case '教师职称':
                                updata[a].jszc = data[a][key];
                                break;
                            case '是否外聘':
                                updata[a].sfwp = data[a][key];
                                break;
                            case '开课学院':
                                updata[a].kkxy = data[a][key];
                                break;
                            case '学分':
                                updata[a].xf = data[a][key];
                                break;
                            case '总学时':
                                updata[a].xs = data[a][key];
                                break;
                            case '周学时':
                                updata[a].zxs = data[a][key];
                                break;
                            case '起止周':
                                updata[a].qzz = data[a][key];
                                break;
                            case '上课时间':
                                updata[a].sksj = data[a][key];
                                break;
                            case '上课地点':
                                updata[a].skdd = data[a][key];
                                break;
                            case '讲课学时':
                                updata[a].jkxs = data[a][key];
                                break;
                            case '上机学时':
                                updata[a].sjxs = data[a][key];
                                break;
                            case '实验学时':
                                updata[a].syxs = data[a][key];
                                break;
                            case '新开课':
                                updata[a].xkk = data[a][key];
                                break;
                            case '重复课':
                                updata[a].cfk = data[a][key];
                                break;
                            case '双语课':
                                updata[a].syk = data[a][key];
                                break;
                            case '教学班':
                                updata[a].jxb = data[a][key];
                                break;
                            case '行政班':
                                updata[a].xzb = data[a][key];
                                break;
                            case '人数':
                                updata[a].rs = data[a][key];
                                break;
                            case '重修人数':
                                updata[a].cxrs = data[a][key];
                                break;
                        }
                    }
                }
            }
            $.ajax({
                url: "<?=BASE_URL?>index.php/Kc_data/import",
                async: true,
                type: 'POST',
                dataType: 'json',
                data: {
                    'data': updata
                },
                success: function () {
                    /* 关闭弹出层   iframe 中子页面调用父级页面的方法或者选择父级元素用 window.parent */
                    window.parent.document.getElementsByClassName('layui-layer-close1')[0].click();
                },
                error: function (data) {
                    console.log(data);
                    alert('导入失败');
                }
            });
        })
    });
</script>
<script src="<?=BASE_URL?>layuiadmin/my.common.js"></script>
</body>

</html>