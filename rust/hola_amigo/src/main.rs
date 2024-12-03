fn main() {
    println!("Hello, world!");
    for number in (1..4).rev() {
        println!("{number}!");
    }
    let a = [1, 2, 3, 4, 5];

    let nice_slice = &a[1..4];
    println!(
        "equals 1={} 2={}",
        nice_slice == [2, 3, 4],
        *nice_slice == [2, 3, 4]
    );
    // panic!("Array not big enough, more elements needed");
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
