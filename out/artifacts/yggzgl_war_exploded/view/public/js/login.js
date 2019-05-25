window.onload = function() {
    new Vue({
        el: ".login",
        data: {
            tip: "快快乐乐上班，高高兴兴回家!",
            logo: "员工工资管理系统",
            id: "",
            password: "",
            radio: '0'
        },
        methods: {
            toLogin() {
                console.log(this.radio);
                if (!this.id || !this.password) {
                    alert('工号和密码不能为空');
                } else {
                    var flag = this.radio;
                    axios.get('/LoginServlet?flag=' + flag, {
                        params: {
                            id: this.id,
                            psd: this.password
                        }
                    }).then(function(res) {
                        let msg = res['data'];
                        if (msg == 0) {
                            alert('登录超时');
                        } else if (msg == -1) {
                            alert('用户名不存在或者密码错误');
                        } else {
                            window.location.href = "http://localhost:8080/HomeServlet"
                        }
                    })
                }
            }
        }
    })
}