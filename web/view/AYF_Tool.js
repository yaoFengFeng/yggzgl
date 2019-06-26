//数组去重 思路：在数组原型上添加一个去重方法 供所有数组调用去重
Array.prototype.unRepeat = function(){
    var temp = {},len = this.length,arr = [] ;
    for(var i = 0;i < len; i++){
        if(!temp[this[i]]){
            temp[this[i]] = 'aa';
            arr.push(this[i]);          
        }
    }
    return arr;
}
