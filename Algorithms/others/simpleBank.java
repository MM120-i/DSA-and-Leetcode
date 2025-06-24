class Bank {
    private long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {

        if(!valid(account2)){
            return false;
        }

        return withdraw(account1, money) && deposit(account2, money);
    }
    
    public boolean deposit(int account, long money) {
        
        if(!valid(account)){
            return false;
        }

        balance[account - 1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        
        if(!valid(account)){
            return false;
        }

        if(balance[account - 1] < money){
            return false;
        }

        balance[account - 1] -= money;
        return true;
    }

    private boolean valid(int account){

        if(account > 0 && account <= balance.length){
            return true;
        }
        
        return false;
    }
}
