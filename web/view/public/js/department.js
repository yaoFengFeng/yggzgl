window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            alltitles: [],
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
            getRulesData() {
                const that = this;
                axios.get("http://localhost:8080/RulesServlet").then(function(res) {
                    that.alltitles = res.data;
                    console.log(that.alltitles);
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
            this.getRulesData();
        }
    })
}