fn main() {
    let mut index = String::new();
    index.push_str("hola");
    /*
    1. r#" 和 "# 用于创建原始字符串字面量。
    2. 原始字符串字面量中的内容不会进行转义处理。
    3. 可以通过增加 # 的数量来避免与字符串内容中的 # 冲突。
    */
    println!(r#"       "Welcome to..."                      "#);
    println!(r#"                 _   _ _                  "#);
    println!(r#"  _ __ _   _ ___| |_| (_)_ __   __ _ ___  "#);
    println!(r#" | '__| | | / __| __| | | '_ \ / _` / __| "#);
    println!(r#" | |  | |_| \__ \ |_| | | | | | (_| \__ \ "#);
    println!(r#" |_|   \__,_|___/\__|_|_|_| |_|\__, |___/ "#);
    println!(r#"                               |___/      "#);
    println!();
    println!(r###"This is a #quote# inside a raw string.={}"###, index);
    println!();
}
