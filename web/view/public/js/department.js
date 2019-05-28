window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            alltitles: [],
            deptableData: [],
            depIndex: 0,
            depName: "董事局",
            titleIndex: 0,
            upRuleData: [],
            inputDepName: '',
            inputDepPhone: '',
            num: 0,
            inputTitle: '',
            inputBasic: 0.0,
            inputBonus: 0.0,
            inputYearS: 0.0,
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
                axios.get("/DepartmentServlet?flag=1").then(function(res) {
                    that.deptableData = res.data;
                })
            },
            getRulesData() {
                const that = this;
                axios.get("/RulesServlet?flag=1").then(function(res) {
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
                axios.post("/RulesServlet", params).then(function() {
                    alert('更新成功');
                })
            },
            insertTitle() {
                var str = "insert into rules(department,title,basic_salary,bonus,basic_years_salary)" +
                    "values('" + this.depName + "','" + this.inputTitle + "','" + this.inputBasic + "','" + this.inputBonus + "','" + this.inputYearS + "')";
                const that = this;
                axios.get("/RulesServlet?flag=2&str=" + str).then(function(res) {
                    if (res.data) {
                        that.getRulesData();
                        that.inputTitle = '';
                        that.inputBasic = 0;
                        that.inputBonus = 0;
                        that.inputYearS = 0;
                    } else {
                        alert('网络繁忙')
                    }
                })
            },
            undoinsertTitle() {
                this.inputTitle = '';
                this.inputBasic = 0;
                this.inputBonus = 0;
                this.inputYearS = 0;
            },
            updateTitle(index) {
                var basic_salary = this.alltitles[index].basicSalary;
                var bonus = this.alltitles[index].bonus;
                var basic_years_salary = this.alltitles[index].basic_years_salary;
                var title = this.alltitles[index].title;
                var str = "update rules set basic_salary = " +
                    basic_salary + " ,bonus=" + bonus + ",basic_years_salary=" + basic_years_salary + " where department = '" + this.depName + "' and title = '" + title + "'";
                const that = this;
                axios.get("/RulesServlet?flag=2&str=" + str).then(function(res) {
                    if (res.data) {
                        that.getRulesData();
                    } else {
                        alert('网络繁忙')
                    }
                })
            },
            deleteTitle(index) {
                var title = this.alltitles[index].title;
                var str = "delete from rules where department = '" + this.depName + "' and title = '" + title + "'";
                const that = this;
                console.log(str);
                axios.get("/RulesServlet?flag=2&str=" + str).then(function(res) {
                    if (res.data) {
                        that.getRulesData();
                    } else {
                        alert('网络繁忙')
                    }
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