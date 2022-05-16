import Vue from 'vue'
import CryptoJS from 'crypto-js'
export default {//加密
    encrypt(word, keyStr){
        keyStr = keyStr ? keyStr : 'thanks,pig4cloud';
        var key  = CryptoJS.enc.Utf8.parse(keyStr);
        var data = CryptoJS.enc.Utf8.parse(word);
        var encrypted = CryptoJS.AES.encrypt(data, key, {
            iv: key,
            mode: CryptoJS.mode.CFB,
            padding: CryptoJS.pad.NoPadding
        })
        return encrypted.toString()
    }
}