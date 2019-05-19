window.onload = function() {

    var all = new Vue({
        el: '.all',
        data: {
            /* 表格数据 */
            tableData: [],
            /*
             *tableDataKeys：
             * 0. val值可以作为th 表头数据
             * 1. 遍历后台返回的data时 将key value 与表头对应（后台数据是乱序的，所以这是必要的）
             * 2. 导入、导出时 数据库表头（字段名）和Excel表头更换（对应）
             */
            tableDataKeys: [
                { key: 'username', val: "姓名" },
                { key: 'id', val: "工号" },
                { key: 'department', val: "部门" },
                { key: 'title', val: "职位" },
                { key: 'service_time', val: "工龄" },
                { key: 'sex', val: "性别" },
                { key: 'address', val: "住址" },
                { key: 'phone', val: "电话" },
                { key: 'status', val: "状态" },
                { key: 'note', val: "备注" }
            ],

            options: [
                { text: "---选择部门---" },
                { text: "人事部" },
                { text: "财务部" },
                { text: "技术部" },
                { text: "销售部" },
                { text: "监察部" },
                { text: "后勤部" }
            ],
            filename: "",
            showSelf: false
        },
        methods: {
            getusers() {
                const that = this; //axois 中无法直接访问Vue对象（this） 这里先将this赋值给常量 that
                axios.get('http://localhost:8080/StaffManageServlet?flag=1').then(function(res) {
                    that.tableData = res.data
                })
            },
            //监听 select改变 获取index  ele.target.value获取值
            change(ele) {
                console.log(ele.target.selectedIndex)
            },
            showM() {
                this.showSelf = true;
            },
            unshow() {
                this.showSelf = false;
                console.log(showSelf);
            },
            chouseExcel() {
                //触发 ref="filElem" 的input点击事件
                this.$refs.filElem.dispatchEvent(new MouseEvent('click'))
            },
            getFileName(e) {
                this.filename = e.target.value;
                window.importf(e.target);
            },
            startImport() {
                if (!wb) {
                    alert('请先选择数据表文件');
                    return;
                }
                var data = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]);

                /* data是从excel获取的数据
                 * 将excel中的中文表头（data中的属性值）全部改成 与数据库表头一样的英文表头 方便后续插入到数据库中
                 *将data克隆到updata中  利用tableDataKeys 中的key 和 val值将中文表头转换成英文字段名
                 */
                var updata = [];
                var keys = this.tableDataKeys;
                for (var a in data) {
                    updata[a] = {};
                    for (var row in keys) {
                        updata[a][keys[row].key] = data[a][keys[row].val];
                        if (!data[a][keys[row].val]) {
                            updata[a][keys[row].key] = " "
                        }
                    }
                }
                var params = new URLSearchParams(); //处理参数 兼容性不高  可以用babel转换
                params.append('userlist', JSON.stringify(updata));
                console.log(JSON.stringify(updata));
                axios.post('http://localhost:8080/StaffManageServlet', params).then(function(res) {
                    this.showSelf = false;
                    console.log(res.data);
                })
            }
        }
    });

    all.getusers();

}