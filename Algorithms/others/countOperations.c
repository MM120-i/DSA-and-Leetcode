int countOperations(int num1, int num2) {

    int counter = 0;

    while(num1 != 0 && num2 != 0){
        counter++;
        
        if(num1 > num2){
            num1 -= num2;
        }
        else if(num2 >= num1){
            num2 -= num1;
        }
    }

    return counter;
}
