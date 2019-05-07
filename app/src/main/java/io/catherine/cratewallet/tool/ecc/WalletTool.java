package io.catherine.cratewallet.tool.ecc;


import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

import java.math.BigInteger;

import io.catherine.cratewallet.bean.WalletBean;
import io.catherine.cratewallet.tool.LogTool;
import io.catherine.cratewallet.tool.StringTool;

import static org.bitcoinj.core.Utils.HEX;


/**
 * 钱包
 *
 * @date 2018/06/25
 * <p>
 * 创建钱包的配置
 */
public class WalletTool {

    private static String TAG = WalletTool.class.getSimpleName();

    public WalletTool() {
        super();
    }

    public static WalletBean createWallet() {

        try {

            // 比特幣主網參數
            NetworkParameters mainNetParams = MainNetParams.get();
            // 取得私鑰WIF格式
            String privateKeyAsHex = new ECKey().getPrivateKeyAsHex();
            BigInteger privateKeyInt = new BigInteger(1, HEX.decode(privateKeyAsHex.toLowerCase()));
            // 未壓縮
            ECKey privateKey = ECKey.fromPrivate(privateKeyInt, false);

            WalletBean walletBean = new WalletBean();
            walletBean.setPrivateKey(privateKey.getPrivateKeyAsWiF(mainNetParams));
            // 公鑰(長度130)
            walletBean.setPublicKey(privateKey.getPublicKeyAsHex());
            // 產生地址
            walletBean.setAddress(privateKey.toAddress(mainNetParams).toBase58());

            return walletBean;

        } catch (Exception e) {
            LogTool.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return null;

    }

    public static WalletBean createWallet(String privateKeyAsWiFStr) {

        try {

            if (KeyTool.validateBitcoinPrivateKeyWIFStr(privateKeyAsWiFStr)) {
                // 比特幣主網參數
                NetworkParameters mainNetParams = MainNetParams.get();
                // 私鑰WIF格式字串取得ECKey
                ECKey privateKey = DumpedPrivateKey.fromBase58(mainNetParams, privateKeyAsWiFStr).getKey();

                WalletBean wallet = new WalletBean();
                wallet.setPrivateKey(privateKey.getPrivateKeyAsWiF(mainNetParams));
                // 公鑰(長度130)
                wallet.setPublicKey(privateKey.getPublicKeyAsHex());
                // 產生地址
                wallet.setAddress(privateKey.toAddress(mainNetParams).toBase58());

                return wallet;
            }

        } catch (Exception e) {
            LogTool.d(TAG,  e.getMessage());
        }

        return null;


    }

    /* 自动创建钱包信息*/
    public static WalletBean getWalletInfo() {
        return getWalletInfo("");
    }

    /*通过WIF格式的私钥来创建钱包*/
    public static WalletBean getWalletInfo(String privateKeyWIFStr) {
        if (StringTool.isEmpty(privateKeyWIFStr)) {
            return WalletTool.createWallet();
        } else {
            return WalletTool.createWallet(privateKeyWIFStr);

        }

    }

    //通过默认的方式来获取钱包地址
    public static String getWalletAddress() {
        return getWalletInfo("").getAddress();
    }

    //通过WIF格式的私钥来获取钱包地址信息
    public static String getWalletAddress(String privateKeyWIFStr) {
        return getWalletInfo(privateKeyWIFStr).getAddress();
    }

}
