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
                'username',
                'id',
                'department',
                'title',
                'service_time',
                'sex',
                'address',
                'phone',
                'status',
                'note'
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
}