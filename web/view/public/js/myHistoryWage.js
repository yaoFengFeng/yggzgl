window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            years: [2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038, 2039, 2040, 2041, 2042, 2043],
            mouths: ['所有月份', 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            year: '2019',
            mouth: '所有月份',
            th: ["年/月", "工号", "姓名", "部门", "职位", "基本工资", "奖金", "工龄工资", "其他", "总计", "备注"],
            keys: ['date', 'id', 'name', 'department', 'title', 'basic_salary', 'bonus', 'basic_years_salary', "others", "count", "note"],
            wages: [],
            employeesName: '',
            employeesID: '',
            count: 0,
        },
        methods: {
            yearChange() {
                var e = e || window.event;
                this.year = e.target.value;
            },
            //计算总工资
            computCount() {
                var wages = this.wages;
                this.count = 0; //先清空
                for (let i in wages) {
                    this.count += wages[i].count * 1; //*1是为了将string转化为 int
                }
            },
            dateChange() {
                var e = e || window.event;
                this.mouth = e.target.value;
                var str = "";
                // 此处有巨坑 url传参 % 需要将其转义为它的编码格式 %25
                if (this.mouth == '所有月份') {
                    str = " and date like '%25" + this.year + "%25'";
                } else {
                    this.mouth = this.mouth < 10 ? '0' + this.mouth : this.mouth;
                    let ym = this.year + "-" + this.mouth;
                    str = " and date = '" + ym + "'";
                }
                const that = this;
                axios.get("/WageServlet?flag=7&str=" + str).then(function(res) {
                    that.wages = res.data;
                    that.computCount();
                })
            }
        },
        mounted() {
            const that = this;
            //获取当年历史工资
            axios.get("/WageServlet?flag=7&str=").then(function(res) {
                that.wages = res.data;
                //计算总工资
                that.computCount();
            })

        }
    })
}