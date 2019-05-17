<?php
 include ('head.php');
?>
</head>
<body>

<div class="layui-card layadmin-header">
    <div class="layui-breadcrumb" lay-filter="breadcrumb">
        <a lay-href="">主页</a>
        <a><cite>组件</cite></a>
        <a><cite>数据表格</cite></a>
        <a><cite>开启头部工具栏</cite></a>
    </div>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-form layui-card-header layuiadmin-card-header-auto">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            学年学期：
                        </div>
                        <div class="layui-inline">
                            <select lay-filter="xnxq" name="xnxq" id="select_xnxq">
                                <option value=0>全部</option>
                                <?php
                                foreach ($res as $row) {
                                    echo "<option value='$row'>" . $row . "</option>";
                                }
                                ?>
                            </select>
                        </div>
                        <div class="layui-inline">
                            开课学院：
                        </div>
                        <div class="layui-inline">
                            <select lay-filter="kkxy" name="rolename" id="select_xy">
                                <option value=0>全部</option>
                                <?php
                                foreach ($r as $row) {
                                    echo "<option value='$row'>" . $row . "</option>";
                                }
                                ?>
                            </select>
                        </div>
                        <div class="layui-inline">

                            <button class="layui-btn layuiadmin-btn-order" id="imp" lay-submit="">导入该学期数据</button>
                            <button class="layui-btn layuiadmin-btn-order bt_confirm" >
                                清空该学期数据
                            </button>
                            <button class="layui-btn layuiadmin-btn-order bt_exp" lay-submit="">导出学期初预报</button>
                            <a href="" download="学期初预报.xlsx" id="downloadA"></a>

                        </div>
                    </div>
                </div>
                <div class="layui-card-body">
                    <table class="layui-hide" id="test-table-toolbar" lay-filter="test-table-toolbar"></table>

                    <!--                    <script type="text/html" id="test-table-toolbar-toolbarDemo">-->
                    <!--                      <div class="layui-btn-container">-->
                    <!--                        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>-->
                    <!--                        <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>-->
                    <!--                        <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>-->
                    <!--                      </div>-->
                    <!--                    </script>-->
                    <!---->
                    <!--                    <script type="text/html" id="test-table-toolbar-barDemo">-->
                    <!--                      <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
                    <!--                      <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>-->
                    <!--                    </script>-->
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<?=BASE_URL?>layuiadmin/xlsx.full.min.js"></script>
<script src="<?= BASE_URL ?>layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '<?=BASE_URL?>layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'table', 'layer', 'upload'], function () {
        var admin = layui.admin
            , $ = layui.jquery
            , table = layui.table
            , upload = layui.upload, kkxy = 0, xnxq = 0;

        //刷新表格
        function refreshTable() {
            if(xnxq == '0') xnxq = 0;
            if (kkxy == '0') kkxy =0;
            table.reload('test-table-toolbar', {
                url: '<?=site_url('Kc_data/init_data')?>',
                where: {
                    'xnxq': xnxq,  //将学年学期和开课学院以 get方式传递
                    'kkxy': kkxy
                }
            });
        }

        //学年学期发生改变 更新表格
        layui.form.on('select(xnxq)', function (d) {
            xnxq = d.value;
            refreshTable()
        });

        //开课学院发生改变 更新表格
        layui.form.on('select(kkxy)', function (d) {
            kkxy = d.value;
            refreshTable()
        });

        //点击导入该学期数据
        var layer = layui.layer;
        $('#imp').on('click', function () {
            var ts = $(this);
            layer.open({
                type: 2,
                area: ['500px', '400px'],
                title: ts.html(),
                content: '<?=site_url('Init/gzl_init_imp')?> ',
                end:function () {
                    refreshTable();
                }
            });
        });

        //点击清除数据
        $('.bt_confirm').on('click', function () {
            var ts = $(this),
                bt_con = '';
//                bt_url = ts.attr('bt_url');

            //bt_con  根据选择情况对应的提示信息
            if(xnxq && kkxy){
                bt_con = '清空 '+kkxy+ "学院 "+ xnxq +' 学期数据, 确定？';
            }else if(xnxq&&!kkxy){
                bt_con = '清空所有学院 '+ xnxq +' 学期数据, 确定？';
            }else if(!xnxq&&!kkxy){
                bt_con = '清空所有学院所有学期数据, 确定？';
            }else{
                bt_con = '清空'+kkxy+'学院所有学期数据, 确定？';
            }
            layer.confirm(bt_con, function (index) {
                $.ajax({
                    url:"<?=site_url('Kc_data/delete')?>",
                    async:true,
                    type:'get',
                    data:{
                        'xnxq': xnxq,
                        'kkxy':kkxy
                    },
                    success:function () {
                        refreshTable();
                        layer.msg('已清空');
                    }
                });
            });
        });


        //导出数据
        $('.bt_exp').on('click', function () {
            var ts = $(this),
                bt_con = ts.html();
            layer.confirm(bt_con + ', 确定？', function (index) {
                $.ajax({
                    url: "<?=site_url('Kc_data/init_data')?>",
                    async: true,
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        'xnxq': xnxq,
                        'kkxy':kkxy,
                        'page':1,    //page limit 是layui table选项卡中自带的参数 page：要显示的页码 limit:每页要显示的条目
                        'limit':10000   //防止数据遗漏可以将limit 适当增加
                    },
                    success: function (d) {
                        var downLoad = [];
                        var data = d['data'];
                        // 从数据库获取的表头是拼音的 需要替换成中文
                        for (var a in data) {
                            downLoad[a] = {};
                            for (var key in data[a]) {
                                if (data[a].hasOwnProperty(key)) {
                                    switch (key) {
                                        case 'id':
                                            downLoad[a].id = data[a][key];
                                            break;
                                        case 'xnxq':
                                            downLoad[a].学年学期 = data[a][key];
                                            break;
                                        case 'xkkh':
                                            downLoad[a].选课课号 = data[a][key];
                                            break;
                                        case 'kcmc':
                                            downLoad[a].课程名称= data[a][key];
                                            break;
                                        case 'jsxm':
                                            downLoad[a].教师姓名 = data[a][key];
                                            break;
                                        case 'jszc':
                                            downLoad[a].教师职称 = data[a][key];
                                            break;
                                        case 'sfwp':
                                            downLoad[a].是否外聘 = data[a][key];
                                            break;
                                        case 'kkxy':
                                            downLoad[a].开课学院 = data[a][key];
                                            break;
                                        case 'xf':
                                            downLoad[a].学分 = data[a][key];
                                            break;
                                        case 'xs':
                                            downLoad[a].总学时 = data[a][key];
                                            break;
                                        case 'zxs':
                                            downLoad[a].周学时 = data[a][key];
                                            break;
                                        case 'qzz':
                                            downLoad[a].起止周 = data[a][key];
                                            break;
                                        case 'sksj':
                                            downLoad[a].上课时间 = data[a][key];
                                            break;
                                        case 'skdd':
                                            downLoad[a].上课地点 = data[a][key];
                                            break;
                                        case 'sjxs':
                                            downLoad[a].上机学时 = data[a][key];
                                            break;
                                        case 'syxs':
                                            downLoad[a].实验学时 = data[a][key];
                                            break;
                                        case 'xkk':
                                            downLoad[a].新开课 = data[a][key];
                                            break;
                                        case 'cfk':
                                            downLoad[a].重复课 = data[a][key];
                                            break;
                                        case 'syk':
                                            downLoad[a].双语课 = data[a][key];
                                            break;
                                        case 'jxb':
                                            downLoad[a].教学班 = data[a][key];
                                            break;
                                        case 'xzb':
                                            downLoad[a].行政班 = data[a][key];
                                            break;
                                        case 'rs':
                                            downLoad[a].人数 = data[a][key];
                                            break;
                                        case 'cxrs':
                                            downLoad[a].重修人数 = data[a][key];
                                            break;
                                    }
                                }
                            }
                        }
                        layer.close(index);
                        downloadExl(downLoad);  //导出数据
                    },
                    error: function () {
                        alert('导出失败');
                    }
                });
            });
        });

        table.render({
            elem: '#test-table-toolbar'
            , url: '<?=site_url('Kc_data/init_data')?>'
            , title: '用户数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', width: 80, fixed: 'left', unresize: true, sort: true}
                , {field: 'xkkh', title: '选课课号', width: 100, fixed: 'left'}
                , {field: 'kcmc', title: '课程名称', width: 120, fixed: 'left'}
                , {field: 'jsxm', title: '教师姓名', width: 90}
                , {field: 'jszc', title: '教师职称', width: 90}
                , {field: 'sfwp', title: '是否外聘', width: 90}
                , {field: 'kkxy', title: '开课学院', width: 90}
                , {field: 'xf', title: '学分', width: 60}
                , {field: 'xs', title: '总学时', width: 90}
                , {field: 'zxs', title: '周学时', width: 90}
                , {field: 'qzz', title: '起止周', width: 90}
                , {field: 'sksj', title: '上课时间', width: 90}
                , {field: 'skdd', title: '上课地点', width: 90}
                , {field: 'jxb', title: '教学班', width: 90}
                , {field: 'xzb', title: '行政班', width: 90}
                , {field: 'rs', title: '人数', width: 60}
                , {field: 'cxrs', title: '重修人数', width: 90}
            ]]
            , page: true
        });

        //头工具栏事件
        table.on('toolbar(test-table-toolbar)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'getCheckData':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    break;
                case 'getCheckLength':
                    var data = checkStatus.data;
                    layer.msg('选中了：' + data.length + ' 个');
                    break;
                case 'isAll':
                    layer.msg(checkStatus.isAll ? '全选' : '未全选');
                    break;
            }
        });

        //监听行工具事件
        table.on('tool(test-table-toolbar)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                layer.prompt({
                    formType: 2
                    , value: data.email
                }, function (value, index) {
                    obj.update({
                        email: value
                    });
                    layer.close(index);
                });
            }
        });
    });
</script>
<script src="<?=BASE_URL?>layuiadmin/my.common.js"></script>
</body>
</html>
