public class DecimalConvertor {
    // 2진수 문자열 -> 10진수 정수
    public static int binaryToDecimal(String binaryValue) {
        int decimal = 0;
        int length = binaryValue.length();
        for (int i = 0; i < length; i++) {
            decimal += (binaryValue.charAt(i) - '0') * Math.pow(2, length - 1 - i); //정수 변환 * 자릿수 2의 거듭제곱 곱하기
        }
        return decimal;
    }

    // 10진수 정수 -> 2진수 문자열
    public static String decimalToBinary(int decimal) {
        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.insert(0, decimal % 2); // 가장 앞에 나머지 삽입
            decimal /= 2;   // 몫을 업데이트
        }
        return binary.toString();
    }

    // 10진수 소수 -> 2진수 소수
    public static String decimalFractionToBinary(double decimalFraction) {
        StringBuilder binaryFraction = new StringBuilder("0."); // 2진수 소수 초기화
        while (decimalFraction > 0) {
            if(binaryFraction.length() > 32) {
                return "Error: Result too long";    // 변환 결과가 너무 길어지면 중단
            }
            double r = decimalFraction * 2; // 각 반복에서 10진수 소수를 2로 곱함
            if (r >= 1) {
                binaryFraction.append(1);  // 10진수 소수가 1.0보다 크면 2진수 소수에 1추가
                decimalFraction = r - 1;    // 1.0 빼줌
            } else {
                binaryFraction.append(0);   // 10진수 소수가 1.0보다 작으면 2진수 소수에 0추가
                decimalFraction = r;    // 유지
            }
        }
        return binaryFraction.toString();
    }

    // 2진수 소수 -> 10진수
    public static double binaryFractionToDecimal(String binaryFraction) {
        // 구현실패
        return 0.0;
    }
}

class Test {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.print("Enter a binary Value: ");
        String binaryValue = scanner.nextLine();  // 2진수 값 입력받기
        
        int decimalValue = DecimalConvertor.binaryToDecimal(binaryValue);
        System.out.println("Decimal Value: " + decimalValue);  // 입력 받은 2진수 값을 10진수로 변환한 값 출력하기
        
        String binaryConvertedBack = DecimalConvertor.decimalToBinary(decimalValue);
        System.out.println("Binary Value: " + binaryConvertedBack);  // 출력한 10진수를 2진수 값으로 변환한 값 출력하기
        
        scanner.close();
    }
}
