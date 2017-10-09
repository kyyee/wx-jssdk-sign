/**
 * Created by jm1138 on 2017/10/9.
 */
"use strict";

/*
 * 注意：
 * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
 * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
 * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
 *
 * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
 * 邮箱地址：weixin-open@qq.com
 * 邮件主题：【微信JS-SDK反馈】具体问题
 * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
 */
(function () {
    $.get("sign?url=http://localhost", function (data, textStatus, jqXHR) {
        console.log("数据：" + JSON.stringify(data) + "\n状态：" + textStatus + "\n响应头：" + JSON.stringify(jqXHR));
        let success = status === 'success' && data.result >= 0;
        if (success) {
            let sign = data.data;
            console.log("sign：" + JSON.stringify(sign));

            wx.config({
                debug: true,                // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                appId: sign.appId,          // 必填，公众号的唯一标识
                timestamp: sign.timestamp, // 必填，生成签名的时间戳
                nonceStr: sign.nonceStr,   // 必填，生成签名的随机串
                signature: sign.signature, // 必填，签名，见附录1
                jsApiList: [                 // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                    // 所有要调用的 API 都要加到这个列表中
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'onMenuShareQQ',
                    'onMenuShareQZone',
                    'getNetworkType',
                    'getLocation'
                ]
            });

            wx.ready(function () {
                // 处理成功验证，在这里调用 API
                // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
                // 分享到朋友圈
                wx.onMenuShareTimeline({
                    title: '标题',              // 分享标题
                    link: 'http://localhost',   // 分享链接，该链接域名或路径必须与当前页面对应的公众号js安全域名一致
                    imgUrl: '',                 // 分享图标
                    success: function () {     // 用户确认分享后执行的回调函数
                    },
                    cancel: function () {      // 用户取消分享后执行的回调函数
                    }
                });

                // 发送给朋友
                wx.onMenuShareAppMessage({
                    title: '标题',               // 分享标题
                    desc: '测试分享给朋友',      // 分享描述
                    link: 'http://localhost',    // 分享链接，该链接域名或路径必须与当前页面对应的公众号js安全域名一致
                    imgUrl: '',                  // 分享图标
                    type: '',                    // 分享类型，music、video、link，不填默认为link
                    dataUrl: '',                 // 如果type为music或video，则要提供数据链接，默认为空
                    success: function () {      // 用户确认分享后执行的回调函数
                    },
                    cancel: function () {       // 用户取消分享后执行的回调函数
                    }
                });

                // 分享到QQ
                wx.onMenuShareQQ({
                    title: '标题',               // 分享标题
                    desc: '测试分享给朋友',      // 分享描述
                    link: 'http://localhost',    // 分享链接，该链接域名或路径必须与当前页面对应的公众号js安全域名一致
                    imgUrl: '',                  // 分享图标
                    success: function () {      // 用户确认分享后执行的回调函数
                    },
                    cancel: function () {       // 用户取消分享后执行的回调函数
                    }
                });

                // 分享到QQ空间
                wx.onMenuShareQZone({
                    title: '标题',               // 分享标题
                    desc: '测试分享给朋友',      // 分享描述
                    link: 'http://localhost',    // 分享链接，该链接域名或路径必须与当前页面对应的公众号js安全域名一致
                    imgUrl: '',                  // 分享图标
                    success: function () {      // 用户确认分享后执行的回调函数
                    },
                    cancel: function () {       // 用户取消分享后执行的回调函数
                    }
                });

                // 获取网络状态接口
                wx.getNetworkType({
                    success: function (res) {
                        let networkType = res.networkType; // 返回网络类型 2G、3G、4G、WIFI
                        console.log("网络状态为：" + networkType);
                    }
                });

                // 获取地理位置接口
                wx.getLocation({
                    type: 'wgs84',                     // 默认为 wgs84 的 gps 坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                    success: function (res) {
                        let latitude = res.latitude;   // 纬度，浮点数，范围为90 — -90
                        let longitude = res.longitude; // 经度，浮点数，范围为180 — -180
                        let speed = res.speed;         // 速度，以 m/s 计
                        let accuracy = res.accuracy;   // 位置精度
                        console.log("地理纬度：" + latitude + "经度：" + longitude + "速度：" + speed + "位置精度：" + accuracy);
                    }
                });
            });

            wx.error(function (res) {
                // 处理失败验证
                // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                alert('签名验证失败！');
            });
        }
    }, 'json');
})();