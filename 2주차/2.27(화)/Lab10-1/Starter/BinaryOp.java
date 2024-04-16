// 추상 메소드가 하나만 있는 인터페이스를 functional Interface 라고 한다.
// Functional Interface 가 있는 자리에는 람다 표현식이 들어갈 수 있다.

@FunctionalInterface
interface BinaryOp {
    int apply(int left, int right);
}
