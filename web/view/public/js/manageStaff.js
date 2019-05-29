window.onload = function() {

    var all = new Vue({
        el: '.all',
        data: {
            employeesName: "",
            employeesID: "",
            /* 表格数据 */
            tableData: [],
            trUpdate: [],
            depsAndTitles: [],
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

            options: [],
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
            insertDepIndex: 0,
            //插入员工的数据
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
        computed: {
            isShowTr: function(index) {
                this.trUpdate[index] = !this.trUpdate[index];
                return this.trUpdate[index];
            }
        },
        created() {
            const that = this;
            axios.get("/RulesServlet?flag=1").then(function(res) {
                that.depsAndTitles = res.data;
            })
        },
        methods: {
            getusers() {
                const that = this; //axois 中无法直接访问Vue对象（this） 这里先将this赋值给常量 that
                axios.get('http://localhost:8080/StaffManageServlet?flag=1').then(function(res) {
                    that.tableData = res.data;
                    for (let i in that.tableData) {
                        that.trUpdate[i] = false;
                    }
                })
            },
            getdeps() {
                const that = this;
                axios.get('/DepartmentServlet?flag=3').then(function(res) {
                    that.options = res.data;
                    console.log(that.options);
                })
            },
            //监听 select改变 获取index  ele.target.value获取值
            change(ele) {
                const that = this;
                var dep = ele.target.value;
                if (ele.target.selectedIndex == 0) { //选择第一个 option获取所有员工信息
                    that.getusers();
                } else {
                    axios.get('/StaffManageServlet?flag=2&dep=' + dep).then(function(res) {
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
                axios.get('/StaffManageServlet?flag=3&str=' + str).then(function(res) {
                    that.tableData = res.data
                })
            },
            showM() {
                this.showSelf = true;
            },
            unshow() {
                this.showSelf = false;
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
                axios.post('/StaffManageServlet', params).then(function(res) {
                    that.showSelf = false; //隐藏弹出层
                    that.getusers(); //重新获取数据 刷新表格
                    if (res.data == -1) {
                        alert('员工信息中存在未定义的部门名称');
                    }
                })
            },
            //显示tfoot添加员工
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
                var depName = this.insertDatas.department;
                axios.post('/StaffManageServlet?type=1&flag=1&str=' + str + "&dep=" + depName).then(function(res) {
                    if (res.data) {
                        that.getusers();
                        //插入成功之后 清空insertDatas的属性值（不删除属性）
                        Object.keys(that.insertDatas).forEach(key => that.insertDatas[key] = "");
                    }

                })
            },
            unInsert() {
                this.isShowTfoot = false; //隐藏添加行
            },
            deleteUser(index) {
                // console.log(this.tableData[index].id);
                let a = confirm('确定删除员工 ' + this.tableData[index].username + " 的信息?");
                if (a) {
                    let id = this.tableData[index].id;
                    var depName = this.tableData[index].department;
                    this.tableData.splice(index, 1); //删除一条数据
                    let str = "delete from users where id = '" + id + "'";
                    axios.post('/StaffManageServlet?type=0&flag=1&str=' + str + "&dep=" + depName).then(function() {
                        alert("已删除");
                    });
                }
            },
            //修改员工信息
            updateUser(index) {
                for (var i in this.trUpdate) {
                    this.trUpdate[i] = false;
                }
                this.trUpdate[index] = true;
                this.isShowTfoot = true;
                this.isShowTfoot = false;

            },
            //取消修改
            unUpdateUser(index) {
                this.trUpdate[index] = false;
                this.isShowTfoot = true;
                this.isShowTfoot = false;
                console.log(this.trUpdate);
            },
            //确定修改员工信息 更新数据
            toUpdateUser(index) {
                let str = "update users set ";
                for (let key in this.tableData[index]) {
                    let str1 = this.tableData[index][key];
                    if (key == 'service_time') {
                        str1 = this.tableData[index][key].substr(0, str1.length - 1); // 把年去掉
                        console.log(str1);
                    }
                    str += key + " = '" + str1 + "',";
                }
                str = str.substr(0, str.length - 1) + " where id = '" + this.tableData[index]['id'] + "'";
                const that = this;
                axios.post('/StaffManageServlet?type=2&flag=1&str=' + str).then(function() {
                    that.getusers();
                });
            },
            //导出数据到Excel
            outputData() {
                var data = [];
                var item = {};
                var th = this.tableDataKeys;
                for (let i in this.tableData) {
                    item = {};
                    for (let j in th) {
                        item[th[j].val] = this.tableData[i][th[j].key];
                    }
                    data.push(item);
                }
                // console.log(data);
                window.downloadExl(data);
            },
            //插入员工的部门变化 根据部门提供该部门职位 实现部门与职位的二级联动
            insertDepChange() {
                var e = e || window.event;
                this.insertDatas.department = e.target.value; //选择的数据插入到insertDatas中
            }
        },
        mounted() {
            this.getdeps();
        }
    });

    all.getusers();

}