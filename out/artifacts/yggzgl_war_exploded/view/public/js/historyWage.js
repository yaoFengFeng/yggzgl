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
            deps: [],
            wages: [],
            employeesName: '',
            employeesID: '',
            count: 0,
            selDep: '--所有部门--'
        },
        methods: {
            depChange() {
                var e = e || window.event;
                this.selDep = e.target.value;
                var str = "select * from all_wage where department = '" + this.selDep + "'";
                const that = this;
                axios.get("/WageServlet?flag=6&str=" + str).then(function(res) {
                    that.wages = res.data;
                    that.computCount();
                })
            },
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
                if (this.mouth == '所有月份' && this.selDep == '--所有部门--') {
                    str = "select * from all_wage where date like '%25" + this.year + "%25'";
                } else if (this.mouth != '所有月份' && this.selDep == '--所有部门--') {
                    this.mouth = this.mouth < 10 ? '0' + this.mouth : this.mouth;
                    let ym = this.year + "-" + this.mouth;
                    str = "select * from all_wage where date = '" + ym + "'";
                } else if (this.mouth == '所有月份' && this.selDep != '--所有部门--') {
                    str = "select * from all_wage where date like '%25" + this.year + "%25' and department = '" + this.selDep + "'";
                } else {
                    this.mouth = this.mouth < 10 ? '0' + this.mouth : this.mouth;
                    let ym = this.year + "-" + this.mouth;
                    str = "select * from all_wage where date = '" + ym + "' and department = '" + this.selDep + "'";
                }
                console.log(str);
                const that = this;
                axios.get("/WageServlet?flag=6&str=" + str).then(function(res) {
                    that.wages = res.data;
                    that.computCount();
                })
            },
            search() {
                const that = this;
                if (!(that.employeesName || that.employeesID)) {
                    alert('请输入姓名或者工号');
                    return;
                } else if (!that.employeesName && that.employeesID) {
                    var str = " select * from all_wage where user_id = '" + that.employeesID + "'";
                } else if (that.employeesName && !that.employeesID) {
                    var str = " select * from all_wage where name = '" + that.employeesName + "'";
                } else {
                    var str = "select * from all_wage where name = '" + that.employeesName + "' and user_id = '" + that.employeesID + "'";
                }
                axios.get('/WageServlet?flag=6&str=' + str).then(function(res) {
                    that.wages = res.data
                    that.computCount();
                })
            },
            outPutWages() {
                var data = [];
                var item = {};
                var th = this.th;
                for (let i in this.wages) {
                    item = {};
                    for (let j in th) {
                        item[th[j]] = this.wages[i][this.keys[j]];
                    }
                    data.push(item);
                }
                window.downloadExl(data);
            }
        },
        mounted() {
            const that = this;
            //获取部门
            axios.get('/DepartmentServlet?flag=3').then(function(res) {
                that.deps = res.data;
            });
            //获取当年历史工资
            axios.get("/WageServlet?flag=3").then(function(res) {
                that.wages = res.data;
                //计算总工资
                that.computCount();
            })

        }
    })
}