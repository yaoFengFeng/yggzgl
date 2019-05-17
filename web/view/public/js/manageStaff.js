window.onload = function() {
    new Vue({
        el: '.search',
        data: {
            options: [
                { text: "---选择部门---" },
                { text: "人事部" },
                { text: "财务部" },
                { text: "技术部" },
                { text: "销售部" },
                { text: "监察部" },
                { text: "后勤部" }
            ]
        },
        methods: {
            //监听 select改变 获取index  ele.target.value获取值
            change(ele) {
                console.log(ele.target.selectedIndex)
            }
        }
    });

    var table = new Vue({
        el: '.myTable',
        data: {
            tableTitle: [
                "姓名",
                "工号",
                "所属部门",
                "职位",
                "工龄",
                "性别",
                "住址",
                "电话",
                "状态",
                "备注",
                "操作",
            ],
            tableData: [],
            tableDataKeys: [
                { key: 'username', val: "姓名" },
                { key: 'id', val: "工号" },
                { key: 'department', val: "部门" },
                { key: 'title', val: "职位" },
                { key: 'service_time', val: "工龄" },
                { key: 'sex', val: "性别" },
                { key: 'address', val: "住址" },
                { key: 'phone', val: "电话" },
                { key: 'status', val: "状态" },
                { key: 'note', val: "备注" }
            ]

        },
        methods: {
            getusers() {
                const that = this; //axois 中无法直接访问Vue对象（this） 这里先将this赋值给常量 that
                axios.get('http://localhost:8080/StaffManageServlet').then(function(res) {
                    // that.$nextTick(() => {
                    that.tableData = res['data']
                        // })
                        // this.tableData = res['data']; 
                    console.log(res['data']);
                    // return res['data'];
                })
            }
        }
    });
    table.getusers(); //进入页面后 获取表格数据


    new Vue({
        el: ".mask",
        data: {
            filename: "",
        },
        methods: {
            chouseExcel() {
                //触发 ref="filElem" 的input点击事件
                this.$refs.filElem.dispatchEvent(new MouseEvent('click'))
            },
            getFileName(e) {
                this.filename = e.target.value;
                window.importf(e.target);
            },
            startImport() {
                if (!wb) {
                    alert('请先选择数据表文件');
                    return;
                }
                var data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);
                /* data是从excel获取的数据*/
                /* data是从excel获取的数据*/
                console.log(data);
                var updata = [];

                /******
                 * 将excel中的中文表头（data中的属性值）全部改成 与数据库表头一样的拼音表头 方便后续插入到数据库中
                 *将data克隆到updata中
                 ********/
                // for (var a in data) {
                //     updata[a] = {};
                //     for (var key in data[a]) {

                //     }
                // }
            }
        }

    })
}