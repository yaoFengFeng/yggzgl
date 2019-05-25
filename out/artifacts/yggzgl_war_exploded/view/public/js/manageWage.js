window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            years: [2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038, 2039, 2040, 2041, 2042, 2043],
            mouths: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            deps: [],
            th: ["工号", "姓名", "部门", "职位", "基本工资", "奖金", "工龄工资", "其他", "总计", "备注"],
            wages: [],
            keys: ['id', 'name', 'department', 'title', 'basic_salary', 'bonus', 'basic_years_salary', "others", "count", "note"]
        },
        methods: {
            makeWageData() {
                const that = this;
                axios.get("/WageServlet?flag=1").then(function(res) {
                    if (res.data <= 0) {
                        alert("请确保所有员工职位存在")
                    } else {
                        that.getNewWages();
                    }
                })
            },
            insertWageData() {

            },
            getDeps() {
                const that = this;
                axios.get("/DepartmentServlet").then(function(res) {
                    for (let i in res.data) {
                        that.deps.push(res.data[i].department)
                    }
                })
            },
            getNewWages() {
                const that = this;
                axios.get("/WageServlet?flag=2").then(function(res) {
                    that.wages = res.data;
                    console.log(that.wages);
                })
            },
            getHistoryWages() {
                const that = this;
                axios.get("/WageServlet?flag=3").then(function(res) {
                    that.wages = res.data;
                })
            }
        },
        mounted() {
            this.getDeps();
            // this.getHistoryWages();
        }
    })
}