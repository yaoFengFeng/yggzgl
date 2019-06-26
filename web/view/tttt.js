function Str(name) {
    this.name = name;
    Str.prototype.fn = function() {
        let p = name.length;
        alert(p);
    }
}
let fun = new Str("哈哈，你很多很多");
let a = fun.fn;
console.log(fun);
a();