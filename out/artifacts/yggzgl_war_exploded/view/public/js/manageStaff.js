window.onload = function() {

    var all = new Vue({
        el: '.all',
        data: {
            employeesName: "",
            employeesID: "",
            /* 表格数据 */
            tableData: [],
            /*
             *tableDataKeys：
             * 0. val值可以作为th 表头数据
             * 1. 遍历后台返回的data时 将key value 与表头对应（后台数据是乱序的，所以这是必要的）
             * 2. 导入、导出时 数据库表头（字段名）和Excel表头更换（对应）
             */
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
            ],

            options: [
                { text: "---所有部门---" },
                { text: "人事部" },
                { text: "财务部" },
                { text: "技术部" },
                { text: "销售部" },
                { text: "监察部" },
                { text: "后勤部" }
            ],
            titles: [
                { text: "" },
                { text: "经理" },
                { text: "工程师" },
                { text: "组长" },
                { text: "助理" },
                { text: "实习生" },
                { text: "职员" },
                { text: "主任" }
            ],
            filename: "",
            showSelf: false,
            isShowTfoot: false,
            insertDatas: {
                username: "",
                id: "",
                department: "",
                title: "",
                service_time: "",
                sex: "",
                address: "",
                phone: "",
                status: "",
                note: ""
            }
        },
        methods: {
            getusers() {
                const that = this; //axois 中无法直接访问Vue对象（this） 这里先将this赋值给常量 that
                axios.get('http://localhost:8080/StaffManageServlet?flag=1').then(function(res) {
                    that.tableData = res.data
                })
            },
            //监听 select改变 获取index  ele.target.value获取值
            change(ele) {
                const that = this;
                var dep = ele.target.value;
                if (ele.target.selectedIndex == 0) { //选择第一个 option获取所有员工信息
                    that.getusers();
                } else {
                    axios.get('http://localhost:8080/StaffManageServlet?flag=2&dep=' + dep).then(function(res) {
                        that.tableData = res.data
                    })
                }

            },
            search() {
                const that = this;
                if (!(that.employeesName || that.employeesID)) {
                    alert('请输入姓名或者工号');
                    return;
                } else if (!that.employeesName && that.employeesID) {
                    var str = " id = '" + that.employeesID + "'";
                } else if (that.employeesName && !that.employeesID) {
                    var str = " username = '" + that.employeesName + "'";
                } else {
                    var str = " username = '" + that.employeesName + "' and id = '" + that.employeesID + "'";
                }
                axios.get('http://localhost:8080/StaffManageServlet?flag=3&str=' + str).then(function(res) {
                    that.tableData = res.data
                })
            },
            showM() {
                this.showSelf = true;
            },
            unshow() {
                this.showSelf = false;
                console.log(showSelf);
            },
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

                /* data是从excel获取的数据
                 * 将excel中的中文表头（data中的属性值）全部改成 与数据库表头一样的英文表头 方便后续插入到数据库中
                 *将data克隆到updata中  利用tableDataKeys 中的key 和 val值将中文表头转换成英文字段名
                 */
                var updata = [];
                var keys = this.tableDataKeys;
                for (var a in data) {
                    updata[a] = {};
                    for (var row in keys) {
                        updata[a][keys[row].key] = data[a][keys[row].val];
                        if (!data[a][keys[row].val]) {
                            updata[a][keys[row].key] = " " // !!!为了防止JSON.stringify(updata)时 将空值的属性忽略 导致后台提取时报错
                        }
                    }
                }
                var params = new URLSearchParams(); //处理参数 兼容性不高  可以用babel转换
                params.append('userlist', JSON.stringify(updata));
                params.append('flag', 0);
                const that = this;
                axios.post('http://localhost:8080/StaffManageServlet', params).then(function(res) {
                    that.showSelf = false; //隐藏弹出层
                    that.getusers(); //重新获取数据 刷新表格
                    console.log(res.data); //返回1 表示导入成功 可以后期加一些提示
                })
            },
            showTfoot() {
                this.isShowTfoot = !this.isShowTfoot;
            },
            toInserUser() {
                var str1 = "";
                var str2 = "";
                const that = this;
                for (key in this.insertDatas) {
                    if (this.insertDatas[key]) {
                        str1 += key + ",";
                        str2 += "'" + this.insertDatas[key] + "',"
                    }
                }
                str1 = str1.substr(0, str1.length - 1);
                str2 = str2.substr(0, str2.length - 1);
                var str = "insert into users(" + str1 + ") values(" + str2 + ")"
                axios.post('http://localhost:8080/StaffManageServlet?flag=1&str=' + str).then(function(res) {
                    console.log(res.data);
                    if (res) {
                        that.getusers();
                    }
                })
            },
            unInsert() {
                this.isShowTfoot = false;
            },
            insertData(key, value) {
                console.log(value);
                // this.insertDatas[key] = e.target.value;
            }
        }
    });

    all.getusers();

}