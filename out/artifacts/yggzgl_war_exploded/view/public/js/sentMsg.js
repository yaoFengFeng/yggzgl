window.onload = function() {
    new Vue({
        el: ".all",
        data: {
            title: "",
            msg: ""
        },
        methods: {
            toSentMsg() {
                var msg = this.msg;
                var title = this.title;
                axios.get("/RegisterServlet?flag=1&msg=" + msg + "&title=" + title).then(function(res) {
                    if (res.data) {
                        alert('已发送通知')
                    } else {
                        alert('网络繁忙')
                    }
                })
            }
        }

    })
}