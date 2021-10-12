package ru.gb.hw04.task1;

public class Main {
    
    private static final Object sync = new Object();
    private enum chars {
        A, B, C;
        private static final chars[] values = values();
        
        public chars next() {
            return values[(this.ordinal()+1) % values.length];
        }
    }
    private static chars currentChar = chars.A;
    
    public static void main(String[] args) {
        Thread t1 = makeThread(chars.A);
        t1.setPriority(5);
        t1.start();
        
        Thread t2 = makeThread(chars.B);
        t2.setPriority(3);
        t2.start();
        
        Thread t3 = makeThread(chars.C);
        t3.setPriority(1);
        t3.start();
    }
    
    private static Thread makeThread(chars threadChar) {
        return new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (sync) {
                        while (currentChar != threadChar) {
                            sync.wait();
                        }
    
                        String str = "";
                        
                        if (threadChar.equals(chars.C)) {
                            str = (i == 4) ? "\n" :  "|";
                        }
                        
                        System.out.print(currentChar + str);
                        currentChar = threadChar.next();
                        sync.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
