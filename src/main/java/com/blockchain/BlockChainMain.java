package com.blockchain;

import java.util.ArrayList;

public class BlockChainMain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public static int difficulty = 5;

    public static boolean validateChain(ArrayList<Block> blockchain) {
        if (!validateBlock(blockchain.get(0), null)) {
          return false;
        }
 
        for (int i = 1; i < blockchain.size(); i++) {
          Block currentBlock = blockchain.get(i);
          Block previousBlock = blockchain.get(i - 1);
 
          if (!validateBlock(currentBlock, previousBlock)) {
            return false;
          }
        }
 
        return true;
    }

    public static boolean validateBlock(Block newBlock, Block previousBlock){
        if( previousBlock == null){
            if (newBlock.previousHash != null)
                return false;
            
            if( newBlock.previousHash != null)
                return false;

            if( newBlock.currentHash == null || !newBlock.calculateHash().equals(newBlock.currentHash))
                return false;

            return true;
        } else {
            if ( newBlock != null){
                if (previousBlock.index + 1 != newBlock.index){
                    return false;
                }

                if ( newBlock.previousHash == null || !newBlock.previousHash.equals(previousBlock.currentHash)){
                    return false;
                }

                return true;
            }

            return false;
        }
    }

}
