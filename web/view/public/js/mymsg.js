window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            msgs: [],
            regist: true,
            read: "已读",
            unRead: "未读",
            isRead: false,
            title: "",
            msg: "",
            flag: 1,
            toshow: 1,
            isRegist: ""
        },
        methods: {
            showMsg(index) {
                this.toshow = 0;
                this.title = this.msgs[index].title;
                this.msg = this.msgs[index].msg;
                if (this.msgs[index].status == 0 || this.msgs[index].status == '0') {
                    var id = this.msgs[index].id;
                    axios.get("/MsgServlet?flag=2&id=" + id);
                }
                this.msgs[index].status = 1;
            },
            readOver() {
                this.toshow = 1
            },
            beRegister() {
                this.regist = !this.regist;
                console.log(this.regist);
                this.isRegist = this.regist ? "不接收通知" : "去接收通知";
                if (this.regist) {
                    axios.get("/RegisterServlet?flag=3").then(function(res) {
                        res.data ? alert('已订阅通知') : alert('网络繁忙')
                    })
                } else {
                    axios.get("/RegisterServlet?flag=4").then(function(res) {
                        res.data ? alert('取消通知') : alert('网络繁忙')
                    })
                }
            }
        },
        mounted() {
            const that = this;
            axios.post("/MsgServlet").then(function(res) {
                that.msgs = res.data;
            });
            axios.get("/RegisterServlet?flag=2").then(function(res) {
                if (res.data) {
                    that.isRegist = "不接收通知";
                    that.regist = true;
                } else {
                    that.isRegist = "去接收通知";
                    that.regist = false;
                }
                console.log(that.regist);
            })
        }
    })
}