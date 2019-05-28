window.onload = function() {
    var vm = new Vue({
        el: '.all',
        data: {
            realTime: 'abc',
            active: true,
            iframeIndex: 0,
            lis: [
                [{
                    icon: '#icon-CRM_icon_shouye',
                    text: '主页',
                }, {
                    icon: '#icon-CRM_icon_jichuguanli',
                    text: '部门管理',
                }, {
                    icon: '#icon-CRM_icon_tianjiakehu',
                    text: '员工管理',
                }, {
                    icon: '#icon-CRM_icon_liebiao',
                    text: '历史工资',
                }, {
                    icon: '#icon-CRM_icon_kehu',
                    text: '工资管理',
                }, {
                    icon: '#icon-CRM_icon_dingdan',
                    text: '安全设置',
                }, {
                    icon: '#icon-CRM_icon_dingdan',
                    text: '发布消息',
                }],
                [{
                    icon: '#icon-CRM_icon_shouye',
                    text: '主页',
                }, {
                    icon: '#icon-CRM_icon_jichuguanli',
                    text: '部门管理',
                }, {
                    icon: '#icon-CRM_icon_tianjiakehu',
                    text: '员工管理',
                }, {
                    icon: '#icon-CRM_icon_liebiao',
                    text: '历史工资',
                }, {
                    icon: '#icon-CRM_icon_kehu',
                    text: '工资管理',
                }],
                [{
                    icon: '#icon-CRM_icon_shouye',
                    text: '主页',
                }, {
                    icon: '#icon-CRM_icon_liebiao',
                    text: '历史工资',
                }, {
                    icon: '#icon-CRM_icon_wode',
                    text: '个人信息',
                }, {
                    icon: '#icon-CRM_icon_wode',
                    text: '我的消息',
                }]
            ],
            iframes: [
                [
                    'view/formDetails.html',
                    'view/departmentManagement.html',
                    'view/manageStaff.html',
                    'view/historyWage.html',
                    'view/manageWage.html',
                    'view/personal.html',
                    'view/sentMessage.html',
                ],
                [
                    'view/formDetails.html',
                    'view/departmentManagement.html',
                    'view/manageStaff.html',
                    'view/historyWage.html',
                    'view/manageWage.html'
                ],
                [
                    'view/formDetails.html',
                    'view/myHistoryWage.html',
                    'view/personal.html',
                    'view/myMsg.html'
                ]
            ],
            username: "",
            type: 2,
            active: 0,
            dep: ""
        },
        created: function() {
            const that = this;
            axios.post('/HomeServlet').then(function(res) {
                that.username = res.data[0];
                that.type = res.data[1] - 1;
            })
        },

        mounted() {
            // 页面加载完后显示当前时间
            this.realTime = this.dealWithTime(new Date())
                // 定时刷新时间
            let _this = this
                // 定时器
            this.timer = setInterval(function() {
                _this.realTime = _this.dealWithTime(new Date()) // 修改数据date
            }, 1000)
        },
        methods: {
            toIfream(tab, i) {
                this.lis.forEach(function(item) {
                    item.showBg = false;
                });
                this.iframeIndex = i;
            },

            //    定时器
            dealWithTime(data) {
                let formatDateTime
                let Y = data.getFullYear()
                let M = data.getMonth() + 1
                let D = data.getDate()
                let H = data.getHours()
                let Min = data.getMinutes()
                let S = data.getSeconds()
                let W = data.getDay()
                H = H < 10 ? ('0' + H) : H
                Min = Min < 10 ? ('0' + Min) : Min
                S = S < 10 ? ('0' + S) : S
                switch (W) {
                    case 0:
                        W = '天'
                        break
                    case 1:
                        W = '一'
                        break
                    case 2:
                        W = '二'
                        break
                    case 3:
                        W = '三'
                        break
                    case 4:
                        W = '四'
                        break
                    case 5:
                        W = '五'
                        break
                    case 6:
                        W = '六'
                        break
                    default:
                        break
                }
                formatDateTime = Y + '年' + M + '月' + D + '日 ' + "\n" + H + ':' + Min + ':' + S + ' 星期' + W
                return formatDateTime;
            },
            exit() {
                window.location.href = 'http://localhost:8080/ExitServlet';
            }
        },
        destroyed() {
            if (this.timer) {
                clearInterval(this.timer);
            }

        }
    });
}