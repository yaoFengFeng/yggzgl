window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            th: ["年/月", "工号", "姓名", "部门", "职位", "基本工资", "奖金", "工龄工资", "其他", "总计", "备注"],
            wages: [],
            keys: ['date', 'id', 'name', 'department', 'title', 'basic_salary', 'bonus', 'basic_years_salary', "others", "count", "note"],
            date: "",
            toallIndex: 0
        },
        methods: {
            makeWageData() {
                const that = this;
                var ym = this.getDate();
                if (ym != this.wages[0].date) {
                    axios.get("/WageServlet?flag=1").then(function(res) {
                        if (res.data <= 0) {
                            alert("请确保所有员工职位存在")
                        } else {
                            that.getNewWages();
                        }
                    })
                } else {
                    alert('本月工资已生成')
                }
            },
            getNewWages() {
                const that = this;
                axios.get("/WageServlet?flag=2").then(function(res) {
                    that.wages = res.data;
                })
            },
            getDate() {
                var date = new Date;
                var year = date.getFullYear();
                var month = date.getMonth() + 1;
                month = month < 10 ? '0' + month : month; //小于十月前面补0
                var str = year + "-" + month;
                return str;
            },
            insertWageData() {
                const that = this;
                axios.get("/WageServlet?flag=5").then(function(res) {
                    if (res.data) {
                        alert('已发布本月工资')
                    };
                })
            },
            updateWage() {
                let updata = [];
                let updataItem = {};
                for (let i in this.wages) {
                    if (this.wages[i].others || this.wages[i].note) {
                        updataItem = {};
                        updataItem.id = this.wages[i].id;
                        updataItem.others = this.wages[i].others;
                        updataItem.count = this.wages[i].count;
                        updataItem.note = this.wages[i].note;
                        updata.push(updataItem);
                    }
                }
                var params = new URLSearchParams(); //处理参数 兼容性不高  可以用babel转换
                params.append('updatalist', JSON.stringify(updata));
                params.append('flag', 0);
                const that = this;
                axios.post('http://localhost:8080/WageServlet', params).then(function(res) {
                    if (res) {
                        that.getNewWages(); //重新获取数据 刷新表格
                        alert('已修改')
                    } else {
                        alert('网络繁忙')
                    }
                })
            },
            updateCount(index) {
                let others = (this.wages[index].others) * 1;
                let basic_salary = (this.wages[index].basic_salary) * 1
                let bonus = (this.wages[index].bonus) * 1
                let basic_years_salary = (this.wages[index].basic_years_salary) * 1
                this.wages[index].count = others + basic_salary + bonus + basic_years_salary;
            },
            rightShow(index) {
                var e = e || window.event;
                let ul = document.getElementById("myul");
                ul.style.display = 'block';
                ul.style.left = e.clientX + 'px';
                ul.style.top = e.clientY + 15 + 'px';
                this.toallIndex = index;
            },
            toall() {
                for (let i in this.wages) {
                    if (i != this.toallIndex) {
                        this.wages[i].others = this.wages[this.toallIndex].others;
                        this.wages[i].note = this.wages[this.toallIndex].note;
                        this.updateCount(i);
                    }
                }
                let ul = document.getElementById("myul");
                ul.style.display = 'none';
            },
            unshoeUl() {
                let ul = document.getElementById("myul");
                ul.style.display = 'none';
            }
        },
        mounted() {
            this.getNewWages();
        }
    })
};