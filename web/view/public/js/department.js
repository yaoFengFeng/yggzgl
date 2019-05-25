window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            alltitles: [],
            deptableData: [],
            depIndex: 0,
            depName: "技术部",
            titleIndex: 0,
            upRuleData: [],
            inputDepName: '',
            inputDepPhone: '',
            num: 0
        },
        methods: {
            titleChange(e) {
                this.titleIndex = e.target.selectedIndex;
            },
            depChange(depanme, index) {
                this.depName = depanme;
                this.num = index;
                for (let i = 0; i < this.alltitles.length; i++) {
                    if (depanme == this.alltitles[i].departmentName) {
                        this.titleIndex = i;
                        return;
                    }
                }
            },
            getdepData() {
                const that = this;
                axios.get("http://localhost:8080/DepartmentServlet?flag=1").then(function(res) {
                    that.deptableData = res.data;
                })
            },
            getRulesData() {
                const that = this;
                axios.get("http://localhost:8080/RulesServlet").then(function(res) {
                    that.alltitles = res.data;
                })
            },
            upRule() {
                this.upRuleData = [];
                for (let i = 0; i < this.alltitles.length; i++) {
                    if (this.depName == this.alltitles[i].departmentName) {
                        this.upRuleData.push(this.alltitles[i]);
                    }
                }
                var ruledata = JSON.stringify(this.upRuleData);
                var params = new URLSearchParams(); //处理参数 兼容性不高  可以用babel转换
                params.append('ruledata', ruledata);
                axios.post("http://localhost:8080/RulesServlet?", params).then(function() {
                    alert('更新成功');
                })
            },
            insertDep() {
                var str = "insert into department(department,phone) values('" + this.inputDepName + "','" + this.inputDepPhone + "')";
                const that = this;
                axios.post("/DepartmentServlet?str=" + str).then(function(res) {
                    if (res.data > 0) {
                        that.getdepData();
                        that.inputDepName = '';
                        that.inputDepPhone = '';
                    } else {
                        alert("网络繁忙");
                    }
                })
            },
            undoinsertDep() {
                this.inputDepName = '';
                this.inputDepPhone = '';
            },
            deleteDep(index) {
                var id = this.deptableData[index].id;
                var str = "delete from department where id = " + id;
                console.log(str);
                const that = this;
                var i = confirm("删除部门会将部门中所有员工删除，确定删除吗？");
                if (i) {
                    axios.get("/DepartmentServlet?flag=2&str=" + str).then(function(res) {
                        if (res.data > 0) {
                            that.getdepData();
                        } else {
                            alert("网络繁忙");
                        }
                    })
                }

            }
        },
        mounted() {
            this.getdepData();
            this.getRulesData();
        }
    })
}