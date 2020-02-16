<template>
  <div class="safe-box">
       <div class="safe-header">
            <div class="header-content">
                <!-- <nuxt-link class="logo-a" to="/" alt="爱内购"><img src="../static/images/aLogo.png"/></nuxt-link> -->
            </div>
        </div>
        <div class="safe-cotent">
            <div class="sc-top">
                <!-- <img src="../static/images/cry.png" /> -->
                <h2 class="st-title">抱歉，您当前的浏览器版本过低，不支持新版商城系统，点击<a href="https://www1.ineigo.com" target="_blank">返回旧版&gt;&gt;</a></h2> 
            </div>
            <div class="sc-bottom">
                <div class="sb-left fl">
                    <h2 class="sl-title">建议扫码使用爱内购商城小程序</h2>
                    <!-- <img src="../static/images/xcx-code.jpg"/> -->
                </div>
                <div class="sb-right fl">
                    <h2 class="sr-title">或者使用以下浏览器</h2>
                    <p>
                        <!-- <a href="https://www.google.cn/chrome/" target="_blank"><img src="../static/images/chrome.png" />谷歌</a> -->
                    </p>
                    <p>
                        <!-- <a href="http://www.firefox.com.cn" target="_blank"><img src="../static/images/firefox.png" />火狐</a> -->
                    </p>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
export default {
    name: 'safe',
    layout: 'public',
    mounted() {
        let tmpTag = 'https:' === document.location.protocol ? false : true;  
        if (tmpTag) {  
            let urls = window.location.href;  
            if (urls.indexOf('localhost') === -1) {  
                urls = urls.replace("http", "https");  
                window.location.href = urls;  
            } 
        } 
    }
}
</script>
<style  lang="scss" scoped>
.safe-box {
    width: 100%;
}
.safe-header {
    width: 100%;
    height: 100px;
    background-color: #fff;
    .header-content {
        position: relative;
        width: 1000px;
        height: 100px;
        margin: 0 auto;
        overflow: hidden;
        .logo-a {
            position: absolute;
            left: 0;
            top: 25px;
            display: block;
            width: 183px;
            height: 51px;
            img {
                width: 100%;
                height: 100%;
            }
        }
    }
}
.safe-cotent {
    width: 1000px;
    margin: 0 auto;
    .sc-top {
        margin-top: 115px;
        text-align: center;
        .st-title {
            margin-top: 28px;
            color: #666;
            font-size: 18px;
            a {
                text-decoration: underline;
                color: #FF2F47;
            }
        }
    }
    .sc-bottom {
        margin-top: 122px;
        text-align: center;
        .sb-left {
            width: 500px;
            .sl-title {
                color:#666;
                font-size: 18px;
            }
            img {
                width: 191px;
                height: 191px;
                margin-top: 30px;
            }
        }
        .sb-right {
            width: 500px;
            text-align: left;
            text-indent: 150px;
            .sr-title {
                color:#999;
                font-size: 18px;
            }
            p {
                margin-top: 40px;
                a {
                    font-size: 16px;
                    color: #999;
                    line-height: 55px;
                    text-decoration: underline;
                    img {
                        width: 55px;
                        height: 55px;
                        vertical-align: middle;
                        margin-right: 15px;
                    }
                }
            }
         
        }
    }
}

</style>

