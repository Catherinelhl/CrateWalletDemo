package io.catherine.cratewallet;
/*
+--------------+---------------------------------
+ author       +   Catherine Liu
+--------------+---------------------------------
+ since        +   2019-05-07 15:47
+--------------+---------------------------------
+ projectName  +   CrateWalletDemo
+--------------+---------------------------------
+ packageName  +   io.catherine.cratewallet
+--------------+---------------------------------
+ description  +  
+--------------+---------------------------------
+ version      +  
+--------------+---------------------------------
*/

import io.catherine.cratewallet.bean.WalletBean;
import io.catherine.cratewallet.tool.ecc.WalletTool;

public class Test {
    public static void main(String[] args) throws Exception {
        WalletBean walletBean = WalletTool.createWallet();

        // 比特幣錢包地址
        String bitcoinAddressStr = walletBean.getAddress();
        // 比特幣私鑰WIF格式
        String bitcoinPrivateKeyWIFStr = walletBean.getPrivateKey();
        // String bitcoinPrivateKeyWIFStr =
        // "5KEKVAm9JbNjd9iVRz6xonNhGafrKmzQLRwGx5G33gXLeUHCfWm";
        // 比特幣公鑰((130 characters [0-9A-F]))
        String bitcoinPublicKeyStr = walletBean.getPublicKey();
        // String bitcoinPublicKeyStr ="046604f1c0ce8029352e4bc2515c07c254ad4ad6116d44cda22d805f7f7d4dd5cdab812c6f9ec1d3d38c6f740e9af609125a416c9c17838d564650ad168c28bd1d";

        System.out.println("bitcoinPrivateKeyWIFStr = " + bitcoinPrivateKeyWIFStr);
        System.out.println("bitcoinPublicKeyStr = " + bitcoinPublicKeyStr);
        System.out.println("bitcoinAddressStr = " + bitcoinAddressStr);
    }

}
