package com.blockchain;

public class Main {
    public static void main(String[] args) {    
        Wallet A = new Wallet(BlockChainMain.blockchain);
        Wallet B = new Wallet(BlockChainMain.blockchain);
        System.out.println("Wallet A Balance: " + A.getBalance());
        System.out.println("Wallet B Balance: " + B.getBalance());
        
        System.out.println("Add two transactions… ");
        Transaction tran1 = A.send(B.publicKey, 10);
        if (tran1!=null){
            BlockChainMain.transactions.add(tran1);
        }
        Transaction tran2 = A.send(B.publicKey, 20);
        if (tran2!=null){
            BlockChainMain.transactions.add(tran2);
        }
        
        Block b = new Block(0, null, BlockChainMain.transactions);
        b.mineBlock(BlockChainMain.difficulty);
        BlockChainMain.blockchain.add(b);
          
        System.out.println("Wallet A Balance: " + A.getBalance());
        System.out.println("Wallet B Balance: " + B.getBalance());
        System.out.println("Blockchain Valid : " + BlockChainMain.validateChain(BlockChainMain.blockchain));
 
    }
}