window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            alltitles: [
                { departmentName: "技术部", title: "技术总监", basicSalary: 5000, bonus: 1000, basic_years_salary: 800 },
                { departmentName: "技术部", title: "工程师", basicSalary: 4000, bonus: 800, basic_years_salary: 600 },
                { departmentName: "技术部", title: "助理", basicSalary: 2000, bonus: 500, basic_years_salary: 0 },
                { departmentName: "销售部", title: "销售总监", basicSalary: 4000, bonus: 800, basic_years_salary: 200 },
                { departmentName: "销售部", title: "主任", basicSalary: 3000, bonus: 600, basic_years_salary: 0 },
                { departmentName: "销售部", title: "实习生", basicSalary: 2000, bonus: 200, basic_years_salary: 0 },
                { departmentName: "人事部", title: "经理", basicSalary: 6000, bonus: 500, basic_years_salary: 0 },
                { departmentName: "人事部", title: "职员", basicSalary: 2000, bonus: 500, basic_years_salary: 0 },
                { departmentName: "人事部", title: "实习生", basicSalary: 2000, bonus: 500, basic_years_salary: 0 },
                { departmentName: "董事局", title: "助理", basicSalary: 2000, bonus: 500, basic_years_salary: 0 }
            ],
            deptableData: [],
            depIndex: 0,
            depName: "技术部",
            titleIndex: 0,
            upRuleData: []
        },
        methods: {
            titleChange(e) {
                this.titleIndex = e.target.selectedIndex;
            },
            depChange(depanme) {
                this.depName = depanme;
                for (let i = 0; i < this.alltitles.length; i++) {
                    if (depanme == this.alltitles[i].departmentName) {
                        this.titleIndex = i;
                        return;
                    }
                }
            },
            getdepData() {
                const that = this;
                axios.get("http://localhost:8080/DepartmentServlet").then(function(res) {
                    that.deptableData = res.data;
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
                axios.post("http://localhost:8080/DepartmentServlet?", params).then(function() {
                    alert('更新成功');
                })
            }

        },
        mounted() {
            this.getdepData();
        }
    })
}