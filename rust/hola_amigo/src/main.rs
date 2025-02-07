fn main() {
    println!("Hello, world!");
    for number in (1..4).rev() {
        println!("{number}!");
    }
    let mut a = [1, 2, 3, 4, 5];
    let nice_slice1 = &a[1..4];
    let nice_slice2 = [2, 3, 4];
    println!("equals 1={} 2={} 3={}", nice_slice1 == [2, 3, 4], *nice_slice1 == [2, 3, 4], nice_slice1 == nice_slice2);

    let s1 = String::from("Hello, ");
    let s2 = String::from("world!");
    let s3 = s1 + &s2; // 注意 s1 被移动了，不能继续使用
    let fs = "a" + "b";
    // panic!("Array not big enough, more elements needed");
}

fn first_word(s: &String) -> usize {
    let bytes = s.as_bytes();
    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return i;
        }
    }
    s.len()
}


// cargo test --package hola_amigo --bin hola_amigo -- tests --show-output
// cargo test --package hola_amigo --bin hola_amigo -- tests::fortytwo_is_bigger_than_thirtytwo --exact --show-output
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn ten_is_bigger_than_eight() {
        assert_eq!(10, bigger(10, 8));
    }

    #[test]
    fn fortytwo_is_bigger_than_thirtytwo() {
        assert_eq!(42, bigger(32, 42));
    }

    #[test]
    fn equal_numbers() {
        assert_eq!(42, bigger(42, 42));
    }
}
