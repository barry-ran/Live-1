package com.horen.domain.live;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeLive2 {

    /**
     * code : 0
     * msg : 操作成功
     * data : {"count":117,"lists":[{"title":"蜜糖","name":"jsonmitang.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwvecxt5ywj307s08040d.jpg","number":"93","is_badge":1,"type":"pingtai","level":"1"},{"title":"皮卡丘","name":"jsonpikaqiu.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwxkhcwf76j30a407lgpe.jpg","number":"97","is_badge":1,"type":"pingtai","level":"2"},{"title":"夏沫","name":"jsonxiamo.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwvefimbtmj30bd0ao0ye.jpg","number":"97","is_badge":1,"type":"pingtai","level":"3"},{"title":"小宝贝","name":"jsonxiaobaobei.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fw7u4v52woj304m04cjs9.jpg","number":"160","is_badge":1,"type":"pingtai","level":"4"},{"title":"艾克","name":"jsonaike.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fs2fvatvwwj203c03cmxk.jpg","number":"120","is_badge":1,"type":"pingtai","level":"5"},{"title":"卡哇伊","name":"jsonkawayi.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwvedj3ts3j30ac078n0r.jpg","number":"99","is_badge":1,"type":"pingtai"},{"title":"欲梦","name":"jsonyumeng.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1frc0qchto4j20e80e9wgd.jpg","number":"34","is_badge":1,"type":"pingtai"},{"title":"夜艳","name":"jsonyeyan.txt","img":"https://ws1.sinaimg.cn/large/0070NZDLgy1fpwds8swwhj302q02r74i.jpg","number":"12","is_badge":1,"type":"pingtai"},{"title":"Dancelife","name":"jsonDancelife.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fw834mqtmbj30sg0sgjut.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"幽梦","name":"jsonyoumeng.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fupe5f1jjtj304103owee.jpg","number":"142","is_badge":1,"type":"pingtai"},{"title":"鲍鱼","name":"jsonbaoyu.txt","img":"https://i.loli.net/2018/07/26/5b5956ef49cf2.jpg","number":"28","is_badge":1,"type":"pingtai"},{"title":"YOBF","name":"jsonYOBF.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwmqsluhscj304q04qt8o.jpg","number":"61","is_badge":1,"type":"pingtai"},{"title":"花间","name":"jsonhuajian.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwmqgltbsqj30e80e8wg0.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"兔宝宝","name":"jsontubaobao.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqesr0rtj304v04yab1.jpg","number":"51","is_badge":1,"type":"pingtai"},{"title":"爱恋","name":"jsonailian.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqmf39xqj30sg0sgwij.jpg","number":"57","is_badge":1,"type":"pingtai"},{"title":"903娱乐","name":"json903yule.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fwmqvf02ijj30sg0sgwib.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"抖阴","name":"jsondouyin.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwmqq9l066j304p04jt8u.jpg","number":"9","is_badge":1,"type":"pingtai"},{"title":"九尾狐","name":"jsonjiuweihu.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqo8bw08j30sg0sgn0s.jpg","number":"6","is_badge":1,"type":"pingtai"},{"title":"伊甸园","name":"jsonyidianyuan.txt","img":"https://i.loli.net/2018/10/02/5bb391102b037.png","number":"30","is_badge":1,"type":"pingtai"},{"title":"小美","name":"jsonxiaomei.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucp4kz968j303c03caab.jpg","number":"33","is_badge":1,"type":"pingtai"},{"title":"筱颖","name":"jsonxiaoying.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsyulcw2g9j204602waa4.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"小蜜蜂","name":"jsonxiaomifeng.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucpl34v35j30500503zi.jpg","number":"28","is_badge":1,"type":"pingtai"},{"title":"娇媚","name":"jsonjiaomei.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu14duxmp6j303y03f745.jpg","number":"53","is_badge":1,"type":"pingtai"},{"title":"欧美FEATURED","name":"jsonoumeiFEATURED.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"81","is_badge":1,"type":"pingtai"},{"title":"欧美FEMALE","name":"jsonoumeiFEMALE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"82","is_badge":1,"type":"pingtai"},{"title":"欧美MALE","name":"jsonoumeiMALE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"84","is_badge":1,"type":"pingtai"},{"title":"欧美COUPLE","name":"jsonoumeiCOUPLE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"82","is_badge":1,"type":"pingtai"},{"title":"欧美TRANS","name":"jsonoumeiTRANS.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"85","is_badge":1,"type":"pingtai"},{"title":"好基友","name":"jsonhaojiyou.txt","img":"https://i.loli.net/2018/07/20/5b51545cb1b3a.jpg","number":"32","is_badge":1,"type":"pingtai"},{"title":"sky","name":"jsonsky.txt","img":"https://i.loli.net/2018/07/20/5b51519e4d777.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"娇喘","name":"jsonjiaochuan.txt","img":"https://i.loli.net/2018/07/24/5b56cab496d83.jpg","number":"42","is_badge":1,"type":"pingtai"},{"title":"芒果派","name":"jsonmangguopai.txt","img":"https://i.loli.net/2018/07/24/5b56e95dd5b47.jpg","number":"46","is_badge":1,"type":"pingtai"},{"title":"坦克","name":"jsontanke.txt","img":"https://i.loli.net/2018/09/29/5baf97d97c118.png","number":"10","is_badge":1,"type":"pingtai"},{"title":"夜女郎","name":"jsonyenulang.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fvjduokhnlj3050050dfv.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"蜜桃","name":"jsonmitao.txt","img":"https://i.loli.net/2018/07/24/5b56cfeb2ce8c.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"尤物岛","name":"jsonyouwudao.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fw834re2oxj30sg0sgtc3.jpg","number":"7","is_badge":1,"type":"pingtai"},{"title":"九点","name":"jsonjiudian.txt","img":"https://i.loli.net/2018/09/09/5b9493dce8c83.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"媚颜","name":"jsonmeiyan.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fqi4hjn11nj2040040t8x.jpg","number":"30","is_badge":1,"type":"pingtai"},{"title":"夜来香","name":"jsonyelaixiang.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fqi4lcae2fj204g04gwej.jpg","number":"37","is_badge":1,"type":"pingtai"},{"title":"风流","name":"jsonfengliu.txt","img":"https://i.loli.net/2018/07/24/5b56c3f1b8562.jpg","number":"31","is_badge":1,"type":"pingtai"},{"title":"夜律","name":"jsonyelu.txt","img":"http://ww4.sinaimg.cn/large/0060lm7Tly1fobhirdep9j303c03cwec.jpg","number":"43","is_badge":1,"type":"pingtai"},{"title":"玲珑","name":"jsonlinglong.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1ft3n1upwrrj2055046glp.jpg","number":"37","is_badge":1,"type":"pingtai"},{"title":"浴火","name":"jsonyuhuo.txt","img":"https://i.loli.net/2018/07/20/5b515f91ad065.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"幸运星","name":"jsonxingyunxing.txt","img":"https://i.loli.net/2018/07/20/5b515be78268e.jpg","number":"43","is_badge":1,"type":"pingtai"},{"title":"她秀","name":"jsontaxiu.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8gcg0bbbj204i03la9x.jpg","number":"41","is_badge":1,"type":"pingtai"},{"title":"招财猫","name":"jsonzhaocaimao.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fs2fcicv7tj205c05cwei.jpg","number":"40","is_badge":1,"type":"pingtai"},{"title":"双碟","name":"jsonshuangdie.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8g1ra45ij204l03nt8i.jpg","number":"49","is_badge":1,"type":"pingtai"},{"title":"糖果","name":"jsontangguo.txt","img":"https://i.loli.net/2018/07/20/5b5159200c8ec.jpg","number":"57","is_badge":1,"type":"pingtai"},{"title":"芭乐","name":"jsonbale.txt","img":"https://ww2.sinaimg.cn/large/a15b4afegy1fq8vrze55aj20sg0sg75f.jpg","number":"52","is_badge":1,"type":"pingtai"},{"title":"凝香","name":"jsonningxiang.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8fl7eu28j204z04at8l.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"小惠子","name":"jsonxiaohuizi.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsebs8mzjpj205v03b74d.jpg","number":"49","is_badge":1,"type":"pingtai"},{"title":"夜狐","name":"jsonyehu.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fse9i9zhe4j2047034dft.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"萌妹","name":"jsonmengmei.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1ft3nd13150j204e03waa4.jpg","number":"47","is_badge":1,"type":"pingtai"},{"title":"唇瓣","name":"jsonchunban.txt","img":"https://i.loli.net/2018/06/11/5b1e026d2dbb7.jpg","number":"127","is_badge":1,"type":"pingtai"},{"title":"么么哒","name":"jsonmemeda.txt","img":"https://i.loli.net/2018/07/31/5b5fdbe25edb2.jpg","number":"124","is_badge":1,"type":"pingtai"},{"title":"小性感","name":"jsonxiaoxinggan.txt","img":"https://i.loli.net/2018/07/31/5b5fdc32c6887.jpg","number":"141","is_badge":1,"type":"pingtai"},{"title":"思乐","name":"jsonsile.txt","img":"https://i.loli.net/2018/07/31/5b5fdc126ff21.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"moon","name":"jsonmoon.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu0zi4g62qj303n03bq2s.jpg","number":"42","is_badge":1,"type":"pingtai"},{"title":"小喵宠","name":"jsonxiaomiaochong.txt","img":"https://i.loli.net/2018/07/31/5b5fdc225c7a3.jpg","number":"77","is_badge":1,"type":"pingtai"},{"title":"小迷妹","name":"jsonxiaomimei.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucqv57l1jj30xo0xt7lv.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"兔女郎","name":"jsontunulang.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucolzj7avj303y03pmx2.jpg","number":"117","is_badge":1,"type":"pingtai"},{"title":"爽约","name":"jsonshuangyue.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fv5w4qfowmj30e80e8q68.jpg","number":"6","is_badge":1,"type":"pingtai"},{"title":"艳后","name":"jsonyanhou.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fw84rcmgwsj30sg0sg77i.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"甜咪","name":"jsontianmi.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6oziazuuj303y03tmx1.jpg","number":"164","is_badge":1,"type":"pingtai"},{"title":"睡美人","name":"jsonshuimeiren.txt","img":"https://i.loli.net/2018/07/14/5b49bc21c28bb.jpg","number":"137","is_badge":1,"type":"pingtai"},{"title":"金呗","name":"jsonjinbei.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fr222o0cg8j30ku0kuwei.jpg","number":"20","is_badge":1,"type":"pingtai"},{"title":"蓝猫","name":"jsonlanmao.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6uak5s6zj308c08c762.jpg","number":"148","is_badge":1,"type":"pingtai"},{"title":"美人妆","name":"jsonmeirenzhuang.txt","img":"https://i.loli.net/2018/07/31/5b5fdbf439dd2.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"香蕉","name":"jsonxiangqiao.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvj9j3sdwjj303c03cglu.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"美夕","name":"jsonmeixi.txt","img":"https://i.loli.net/2018/07/20/5b5156bb79432.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"入巷","name":"jsonruxiang.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fsop2jy4klj206o05kwf7.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"持久男","name":"jsonchijiunan.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fqi3dtothcj305c05cjrp.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"小妖","name":"jsonxiaoyao.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fv0bd2efh3j30e80e8wel.jpg","number":"11","is_badge":1,"type":"pingtai"},{"title":"倾心","name":"jsonqingxin.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fse1yr65esj204u04qjs2.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"蓝颜心","name":"jsonlanyanxin.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fqi365igv6j304g04g3z7.jpg","number":"18","is_badge":1,"type":"pingtai"},{"title":"美国AM","name":"jsonmeiguoAM.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fr5cwlyeutj201u01c0si.jpg","number":"63","is_badge":1,"type":"pingtai"},{"title":"美国播","name":"jsonmeiguobo.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fr0bcvdkt9j203h02qwec.jpg","number":"11","is_badge":1,"type":"pingtai"},{"title":"韩国播","name":"jsonhanguobo.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fr0ocinxslj203k010jr6.jpg","number":"12","is_badge":1,"type":"pingtai"},{"title":"约直播","name":"jsonyuezhibo.txt","img":"https://i.loli.net/2018/07/14/5b49bf9314319.jpg","number":"135","is_badge":1,"type":"pingtai"},{"title":"小精灵","name":"jsonxiaojingling.txt","img":"https://i.loli.net/2018/07/14/5b49b3a7d0e0a.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"蜗牛","name":"jsonwoniu.txt","img":"https://i.loli.net/2018/07/14/5b49afc0f40ae.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"偶遇","name":"jsonouyu.txt","img":"https://i.loli.net/2018/07/14/5b4990d0c7a90.jpg","number":"4","is_badge":1,"type":"pingtai"},{"title":"灰灰","name":"jsonhuihui.txt","img":"https://i.loli.net/2018/07/14/5b496a7b3719e.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"猫头鹰","name":"jsonmaotouying.txt","img":"https://i.loli.net/2018/07/14/5b498cb385a08.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"喜欢你","name":"jsonxihuanni.txt","img":"https://i.loli.net/2018/07/14/5b49b93ecb51a.jpg","number":"17","is_badge":1,"type":"pingtai"},{"title":"小魔女","name":"jsonxiaomonu.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fsyt9hwylrj207g05s3yl.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"红颜","name":"jsonhongyan.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu6qu18nf1j3044043wef.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"七仙女","name":"jsonqixiannu.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsyubbc8vrj205g047dga.jpg","number":"117","is_badge":1,"type":"pingtai"},{"title":"加特林","name":"jsonjiatelin.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fsytzgfhqaj206f04mwfg.jpg","number":"136","is_badge":1,"type":"pingtai"},{"title":"夜纯","name":"jsonyechun.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsytlbmap3j205p05g0su.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"杏播","name":"jsonxingbo.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fq6xvf6ufoj305c05cjry.jpg","number":"115","is_badge":1,"type":"pingtai"},{"title":"名流","name":"jsonmingliu.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fs1spk8jkoj204u0433ye.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"小辣椒","name":"jsonxiaolajiao.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsos1z1qhhj205j04bq30.jpg","number":"167","is_badge":1,"type":"pingtai"},{"title":"蚊香社","name":"jsonwenxiangshe.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsoqb5ffq4j2064052mxe.jpg","number":"39","is_badge":1,"type":"pingtai"},{"title":"牵手","name":"jsonqianshou.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsool1hakbj205v053gn1.jpg","number":"139","is_badge":1,"type":"pingtai"},{"title":"花仙子","name":"jsonhuaxianzi.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fr0tcmjjhmj20dw0dwjs7.jpg","number":"156","is_badge":1,"type":"pingtai"},{"title":"疯狂","name":"jsonfengkuang.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsk4j4mbwqj206n05dmxj.jpg","number":"7","is_badge":1,"type":"pingtai"},{"title":"全球鹰","name":"jsonquanqiuying.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fqi4jar1naj205c05c76g.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"捉妖","name":"jsonzhuoyao.txt","img":"https://i.loli.net/2018/05/29/5b0cfbe9e606d.jpg","number":"18","is_badge":1,"type":"pingtai"},{"title":"伊乐","name":"jsonyile.txt","img":"https://i.loli.net/2018/05/29/5b0cff1f739dd.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"土豪","name":"jsontuhao.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fqklayx7c2j203d023jr7.jpg","number":"20","is_badge":1,"type":"pingtai"},{"title":"蓝月亮","name":"jsonlanyueliang.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6svpgz93j304q0440sl.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"情趣","name":"jsonqingqu.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu11evou6aj303d03cwec.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"老司机","name":"jsonlaosiji.txt","img":"https://i.loli.net/2018/09/10/5b954e2d7f39b.png","number":"0","is_badge":1,"type":"pingtai"},{"title":"红妆","name":"jsonhongzhuang.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fs8em07lktj204f03umwz.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"小湿妹","name":"jsonxiaoshimei.txt","img":"https://i.loli.net/2018/09/10/5b9555b762d3b.png","number":"36","is_badge":1,"type":"pingtai"},{"title":"念人旧","name":"jsonnianrenjiu.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjc0tkg9kj301w01tgle.jpg","number":"58","is_badge":1,"type":"pingtai"},{"title":"樱花live","name":"jsonyinghualive.txt","img":"https://ws1.sinaimg.cn/large/0070NZDLgy1fporbicqo1j30sg0sgn2p.jpg","number":"54","is_badge":1,"type":"pingtai"},{"title":"妞妞","name":"jsonniuniu.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu13kpidboj3048044a9z.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"激情","name":"jsonjiqing.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu1054sd9cj304q042dfo.jpg","number":"75","is_badge":1,"type":"pingtai"},{"title":"fire","name":"jsonfire.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu0z1tlm0lj303603674s.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"萌萌哒","name":"jsonmengmengda.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu1100a52kj303m03cwec.jpg","number":"169","is_badge":1,"type":"pingtai"},{"title":"童颜","name":"jsontongyan.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucoafu9myj30dw0dwq5k.jpg","number":"33","is_badge":1,"type":"pingtai"},{"title":"夜涩","name":"jsonyese.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fucqpv6wdej304h0420sm.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"红叶","name":"jsonhongye.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucnzjjtxoj30890a0gmg.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"孤心巷","name":"jsonguxinxiang.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fucnlukcdej3050050q4u.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"翠鸟","name":"jsoncuiniao.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucnaos2wgj305004dwef.jpg","number":"40","is_badge":1,"type":"pingtai"}],"type":"pingtai"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * count : 117
         * lists : [{"title":"蜜糖","name":"jsonmitang.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwvecxt5ywj307s08040d.jpg","number":"93","is_badge":1,"type":"pingtai","level":"1"},{"title":"皮卡丘","name":"jsonpikaqiu.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwxkhcwf76j30a407lgpe.jpg","number":"97","is_badge":1,"type":"pingtai","level":"2"},{"title":"夏沫","name":"jsonxiamo.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwvefimbtmj30bd0ao0ye.jpg","number":"97","is_badge":1,"type":"pingtai","level":"3"},{"title":"小宝贝","name":"jsonxiaobaobei.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fw7u4v52woj304m04cjs9.jpg","number":"160","is_badge":1,"type":"pingtai","level":"4"},{"title":"艾克","name":"jsonaike.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fs2fvatvwwj203c03cmxk.jpg","number":"120","is_badge":1,"type":"pingtai","level":"5"},{"title":"卡哇伊","name":"jsonkawayi.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwvedj3ts3j30ac078n0r.jpg","number":"99","is_badge":1,"type":"pingtai"},{"title":"欲梦","name":"jsonyumeng.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1frc0qchto4j20e80e9wgd.jpg","number":"34","is_badge":1,"type":"pingtai"},{"title":"夜艳","name":"jsonyeyan.txt","img":"https://ws1.sinaimg.cn/large/0070NZDLgy1fpwds8swwhj302q02r74i.jpg","number":"12","is_badge":1,"type":"pingtai"},{"title":"Dancelife","name":"jsonDancelife.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fw834mqtmbj30sg0sgjut.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"幽梦","name":"jsonyoumeng.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fupe5f1jjtj304103owee.jpg","number":"142","is_badge":1,"type":"pingtai"},{"title":"鲍鱼","name":"jsonbaoyu.txt","img":"https://i.loli.net/2018/07/26/5b5956ef49cf2.jpg","number":"28","is_badge":1,"type":"pingtai"},{"title":"YOBF","name":"jsonYOBF.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwmqsluhscj304q04qt8o.jpg","number":"61","is_badge":1,"type":"pingtai"},{"title":"花间","name":"jsonhuajian.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fwmqgltbsqj30e80e8wg0.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"兔宝宝","name":"jsontubaobao.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqesr0rtj304v04yab1.jpg","number":"51","is_badge":1,"type":"pingtai"},{"title":"爱恋","name":"jsonailian.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqmf39xqj30sg0sgwij.jpg","number":"57","is_badge":1,"type":"pingtai"},{"title":"903娱乐","name":"json903yule.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fwmqvf02ijj30sg0sgwib.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"抖阴","name":"jsondouyin.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwmqq9l066j304p04jt8u.jpg","number":"9","is_badge":1,"type":"pingtai"},{"title":"九尾狐","name":"jsonjiuweihu.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fwmqo8bw08j30sg0sgn0s.jpg","number":"6","is_badge":1,"type":"pingtai"},{"title":"伊甸园","name":"jsonyidianyuan.txt","img":"https://i.loli.net/2018/10/02/5bb391102b037.png","number":"30","is_badge":1,"type":"pingtai"},{"title":"小美","name":"jsonxiaomei.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucp4kz968j303c03caab.jpg","number":"33","is_badge":1,"type":"pingtai"},{"title":"筱颖","name":"jsonxiaoying.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsyulcw2g9j204602waa4.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"小蜜蜂","name":"jsonxiaomifeng.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucpl34v35j30500503zi.jpg","number":"28","is_badge":1,"type":"pingtai"},{"title":"娇媚","name":"jsonjiaomei.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu14duxmp6j303y03f745.jpg","number":"53","is_badge":1,"type":"pingtai"},{"title":"欧美FEATURED","name":"jsonoumeiFEATURED.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"81","is_badge":1,"type":"pingtai"},{"title":"欧美FEMALE","name":"jsonoumeiFEMALE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"82","is_badge":1,"type":"pingtai"},{"title":"欧美MALE","name":"jsonoumeiMALE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"84","is_badge":1,"type":"pingtai"},{"title":"欧美COUPLE","name":"jsonoumeiCOUPLE.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"82","is_badge":1,"type":"pingtai"},{"title":"欧美TRANS","name":"jsonoumeiTRANS.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjxt5tz3ij30a007imx6.jpg","number":"85","is_badge":1,"type":"pingtai"},{"title":"好基友","name":"jsonhaojiyou.txt","img":"https://i.loli.net/2018/07/20/5b51545cb1b3a.jpg","number":"32","is_badge":1,"type":"pingtai"},{"title":"sky","name":"jsonsky.txt","img":"https://i.loli.net/2018/07/20/5b51519e4d777.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"娇喘","name":"jsonjiaochuan.txt","img":"https://i.loli.net/2018/07/24/5b56cab496d83.jpg","number":"42","is_badge":1,"type":"pingtai"},{"title":"芒果派","name":"jsonmangguopai.txt","img":"https://i.loli.net/2018/07/24/5b56e95dd5b47.jpg","number":"46","is_badge":1,"type":"pingtai"},{"title":"坦克","name":"jsontanke.txt","img":"https://i.loli.net/2018/09/29/5baf97d97c118.png","number":"10","is_badge":1,"type":"pingtai"},{"title":"夜女郎","name":"jsonyenulang.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fvjduokhnlj3050050dfv.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"蜜桃","name":"jsonmitao.txt","img":"https://i.loli.net/2018/07/24/5b56cfeb2ce8c.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"尤物岛","name":"jsonyouwudao.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fw834re2oxj30sg0sgtc3.jpg","number":"7","is_badge":1,"type":"pingtai"},{"title":"九点","name":"jsonjiudian.txt","img":"https://i.loli.net/2018/09/09/5b9493dce8c83.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"媚颜","name":"jsonmeiyan.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fqi4hjn11nj2040040t8x.jpg","number":"30","is_badge":1,"type":"pingtai"},{"title":"夜来香","name":"jsonyelaixiang.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fqi4lcae2fj204g04gwej.jpg","number":"37","is_badge":1,"type":"pingtai"},{"title":"风流","name":"jsonfengliu.txt","img":"https://i.loli.net/2018/07/24/5b56c3f1b8562.jpg","number":"31","is_badge":1,"type":"pingtai"},{"title":"夜律","name":"jsonyelu.txt","img":"http://ww4.sinaimg.cn/large/0060lm7Tly1fobhirdep9j303c03cwec.jpg","number":"43","is_badge":1,"type":"pingtai"},{"title":"玲珑","name":"jsonlinglong.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1ft3n1upwrrj2055046glp.jpg","number":"37","is_badge":1,"type":"pingtai"},{"title":"浴火","name":"jsonyuhuo.txt","img":"https://i.loli.net/2018/07/20/5b515f91ad065.jpg","number":"38","is_badge":1,"type":"pingtai"},{"title":"幸运星","name":"jsonxingyunxing.txt","img":"https://i.loli.net/2018/07/20/5b515be78268e.jpg","number":"43","is_badge":1,"type":"pingtai"},{"title":"她秀","name":"jsontaxiu.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8gcg0bbbj204i03la9x.jpg","number":"41","is_badge":1,"type":"pingtai"},{"title":"招财猫","name":"jsonzhaocaimao.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fs2fcicv7tj205c05cwei.jpg","number":"40","is_badge":1,"type":"pingtai"},{"title":"双碟","name":"jsonshuangdie.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8g1ra45ij204l03nt8i.jpg","number":"49","is_badge":1,"type":"pingtai"},{"title":"糖果","name":"jsontangguo.txt","img":"https://i.loli.net/2018/07/20/5b5159200c8ec.jpg","number":"57","is_badge":1,"type":"pingtai"},{"title":"芭乐","name":"jsonbale.txt","img":"https://ww2.sinaimg.cn/large/a15b4afegy1fq8vrze55aj20sg0sg75f.jpg","number":"52","is_badge":1,"type":"pingtai"},{"title":"凝香","name":"jsonningxiang.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fs8fl7eu28j204z04at8l.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"小惠子","name":"jsonxiaohuizi.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsebs8mzjpj205v03b74d.jpg","number":"49","is_badge":1,"type":"pingtai"},{"title":"夜狐","name":"jsonyehu.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fse9i9zhe4j2047034dft.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"萌妹","name":"jsonmengmei.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1ft3nd13150j204e03waa4.jpg","number":"47","is_badge":1,"type":"pingtai"},{"title":"唇瓣","name":"jsonchunban.txt","img":"https://i.loli.net/2018/06/11/5b1e026d2dbb7.jpg","number":"127","is_badge":1,"type":"pingtai"},{"title":"么么哒","name":"jsonmemeda.txt","img":"https://i.loli.net/2018/07/31/5b5fdbe25edb2.jpg","number":"124","is_badge":1,"type":"pingtai"},{"title":"小性感","name":"jsonxiaoxinggan.txt","img":"https://i.loli.net/2018/07/31/5b5fdc32c6887.jpg","number":"141","is_badge":1,"type":"pingtai"},{"title":"思乐","name":"jsonsile.txt","img":"https://i.loli.net/2018/07/31/5b5fdc126ff21.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"moon","name":"jsonmoon.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu0zi4g62qj303n03bq2s.jpg","number":"42","is_badge":1,"type":"pingtai"},{"title":"小喵宠","name":"jsonxiaomiaochong.txt","img":"https://i.loli.net/2018/07/31/5b5fdc225c7a3.jpg","number":"77","is_badge":1,"type":"pingtai"},{"title":"小迷妹","name":"jsonxiaomimei.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucqv57l1jj30xo0xt7lv.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"兔女郎","name":"jsontunulang.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucolzj7avj303y03pmx2.jpg","number":"117","is_badge":1,"type":"pingtai"},{"title":"爽约","name":"jsonshuangyue.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fv5w4qfowmj30e80e8q68.jpg","number":"6","is_badge":1,"type":"pingtai"},{"title":"艳后","name":"jsonyanhou.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fw84rcmgwsj30sg0sg77i.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"甜咪","name":"jsontianmi.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6oziazuuj303y03tmx1.jpg","number":"164","is_badge":1,"type":"pingtai"},{"title":"睡美人","name":"jsonshuimeiren.txt","img":"https://i.loli.net/2018/07/14/5b49bc21c28bb.jpg","number":"137","is_badge":1,"type":"pingtai"},{"title":"金呗","name":"jsonjinbei.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fr222o0cg8j30ku0kuwei.jpg","number":"20","is_badge":1,"type":"pingtai"},{"title":"蓝猫","name":"jsonlanmao.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6uak5s6zj308c08c762.jpg","number":"148","is_badge":1,"type":"pingtai"},{"title":"美人妆","name":"jsonmeirenzhuang.txt","img":"https://i.loli.net/2018/07/31/5b5fdbf439dd2.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"香蕉","name":"jsonxiangqiao.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvj9j3sdwjj303c03cglu.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"美夕","name":"jsonmeixi.txt","img":"https://i.loli.net/2018/07/20/5b5156bb79432.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"入巷","name":"jsonruxiang.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fsop2jy4klj206o05kwf7.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"持久男","name":"jsonchijiunan.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fqi3dtothcj305c05cjrp.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"小妖","name":"jsonxiaoyao.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fv0bd2efh3j30e80e8wel.jpg","number":"11","is_badge":1,"type":"pingtai"},{"title":"倾心","name":"jsonqingxin.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fse1yr65esj204u04qjs2.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"蓝颜心","name":"jsonlanyanxin.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fqi365igv6j304g04g3z7.jpg","number":"18","is_badge":1,"type":"pingtai"},{"title":"美国AM","name":"jsonmeiguoAM.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fr5cwlyeutj201u01c0si.jpg","number":"63","is_badge":1,"type":"pingtai"},{"title":"美国播","name":"jsonmeiguobo.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fr0bcvdkt9j203h02qwec.jpg","number":"11","is_badge":1,"type":"pingtai"},{"title":"韩国播","name":"jsonhanguobo.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fr0ocinxslj203k010jr6.jpg","number":"12","is_badge":1,"type":"pingtai"},{"title":"约直播","name":"jsonyuezhibo.txt","img":"https://i.loli.net/2018/07/14/5b49bf9314319.jpg","number":"135","is_badge":1,"type":"pingtai"},{"title":"小精灵","name":"jsonxiaojingling.txt","img":"https://i.loli.net/2018/07/14/5b49b3a7d0e0a.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"蜗牛","name":"jsonwoniu.txt","img":"https://i.loli.net/2018/07/14/5b49afc0f40ae.jpg","number":"50","is_badge":1,"type":"pingtai"},{"title":"偶遇","name":"jsonouyu.txt","img":"https://i.loli.net/2018/07/14/5b4990d0c7a90.jpg","number":"4","is_badge":1,"type":"pingtai"},{"title":"灰灰","name":"jsonhuihui.txt","img":"https://i.loli.net/2018/07/14/5b496a7b3719e.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"猫头鹰","name":"jsonmaotouying.txt","img":"https://i.loli.net/2018/07/14/5b498cb385a08.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"喜欢你","name":"jsonxihuanni.txt","img":"https://i.loli.net/2018/07/14/5b49b93ecb51a.jpg","number":"17","is_badge":1,"type":"pingtai"},{"title":"小魔女","name":"jsonxiaomonu.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fsyt9hwylrj207g05s3yl.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"红颜","name":"jsonhongyan.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu6qu18nf1j3044043wef.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"七仙女","name":"jsonqixiannu.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsyubbc8vrj205g047dga.jpg","number":"117","is_badge":1,"type":"pingtai"},{"title":"加特林","name":"jsonjiatelin.txt","img":"http://ww2.sinaimg.cn/large/87c01ec7gy1fsytzgfhqaj206f04mwfg.jpg","number":"136","is_badge":1,"type":"pingtai"},{"title":"夜纯","name":"jsonyechun.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsytlbmap3j205p05g0su.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"杏播","name":"jsonxingbo.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fq6xvf6ufoj305c05cjry.jpg","number":"115","is_badge":1,"type":"pingtai"},{"title":"名流","name":"jsonmingliu.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fs1spk8jkoj204u0433ye.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"小辣椒","name":"jsonxiaolajiao.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsos1z1qhhj205j04bq30.jpg","number":"167","is_badge":1,"type":"pingtai"},{"title":"蚊香社","name":"jsonwenxiangshe.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsoqb5ffq4j2064052mxe.jpg","number":"39","is_badge":1,"type":"pingtai"},{"title":"牵手","name":"jsonqianshou.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fsool1hakbj205v053gn1.jpg","number":"139","is_badge":1,"type":"pingtai"},{"title":"花仙子","name":"jsonhuaxianzi.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fr0tcmjjhmj20dw0dwjs7.jpg","number":"156","is_badge":1,"type":"pingtai"},{"title":"疯狂","name":"jsonfengkuang.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fsk4j4mbwqj206n05dmxj.jpg","number":"7","is_badge":1,"type":"pingtai"},{"title":"全球鹰","name":"jsonquanqiuying.txt","img":"http://ww4.sinaimg.cn/large/87c01ec7gy1fqi4jar1naj205c05c76g.jpg","number":"1","is_badge":1,"type":"pingtai"},{"title":"捉妖","name":"jsonzhuoyao.txt","img":"https://i.loli.net/2018/05/29/5b0cfbe9e606d.jpg","number":"18","is_badge":1,"type":"pingtai"},{"title":"伊乐","name":"jsonyile.txt","img":"https://i.loli.net/2018/05/29/5b0cff1f739dd.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"土豪","name":"jsontuhao.txt","img":"http://ww3.sinaimg.cn/large/87c01ec7gy1fqklayx7c2j203d023jr7.jpg","number":"20","is_badge":1,"type":"pingtai"},{"title":"蓝月亮","name":"jsonlanyueliang.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu6svpgz93j304q0440sl.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"情趣","name":"jsonqingqu.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fu11evou6aj303d03cwec.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"老司机","name":"jsonlaosiji.txt","img":"https://i.loli.net/2018/09/10/5b954e2d7f39b.png","number":"0","is_badge":1,"type":"pingtai"},{"title":"红妆","name":"jsonhongzhuang.txt","img":"http://ww1.sinaimg.cn/large/87c01ec7gy1fs8em07lktj204f03umwz.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"小湿妹","name":"jsonxiaoshimei.txt","img":"https://i.loli.net/2018/09/10/5b9555b762d3b.png","number":"36","is_badge":1,"type":"pingtai"},{"title":"念人旧","name":"jsonnianrenjiu.txt","img":"http://wx3.sinaimg.cn/mw690/0060lm7Tly1fvjc0tkg9kj301w01tgle.jpg","number":"58","is_badge":1,"type":"pingtai"},{"title":"樱花live","name":"jsonyinghualive.txt","img":"https://ws1.sinaimg.cn/large/0070NZDLgy1fporbicqo1j30sg0sgn2p.jpg","number":"54","is_badge":1,"type":"pingtai"},{"title":"妞妞","name":"jsonniuniu.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu13kpidboj3048044a9z.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"激情","name":"jsonjiqing.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu1054sd9cj304q042dfo.jpg","number":"75","is_badge":1,"type":"pingtai"},{"title":"fire","name":"jsonfire.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fu0z1tlm0lj303603674s.jpg","number":"14","is_badge":1,"type":"pingtai"},{"title":"萌萌哒","name":"jsonmengmengda.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fu1100a52kj303m03cwec.jpg","number":"169","is_badge":1,"type":"pingtai"},{"title":"童颜","name":"jsontongyan.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucoafu9myj30dw0dwq5k.jpg","number":"33","is_badge":1,"type":"pingtai"},{"title":"夜涩","name":"jsonyese.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fucqpv6wdej304h0420sm.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"红叶","name":"jsonhongye.txt","img":"http://wx1.sinaimg.cn/mw690/0060lm7Tly1fucnzjjtxoj30890a0gmg.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"孤心巷","name":"jsonguxinxiang.txt","img":"http://wx2.sinaimg.cn/mw690/0060lm7Tly1fucnlukcdej3050050q4u.jpg","number":"0","is_badge":1,"type":"pingtai"},{"title":"翠鸟","name":"jsoncuiniao.txt","img":"http://wx4.sinaimg.cn/mw690/0060lm7Tly1fucnaos2wgj305004dwef.jpg","number":"40","is_badge":1,"type":"pingtai"}]
         * type : pingtai
         */

        private int count;
        private String type;
        private List<ListsBean> lists;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public List<HomeLive.PingtaiBean> toList() {
            List<HomeLive.PingtaiBean> list = new ArrayList<>();
            for (ListsBean listsBean : lists) {
                list.add(new HomeLive.PingtaiBean(listsBean.getName(), replaceHttpUrl(listsBean.getImg()), listsBean.getNumber(), listsBean.getTitle()));
            }
            return list;
        }

        /**
         * 替换http网址中的\
         */
        public  String replaceHttpUrl(String url) {
            if (TextUtils.isEmpty(url))
                return "";
            return url.replaceAll("\\\\", "");
        }

        public static class ListsBean {
            /**
             * title : 蜜糖
             * name : jsonmitang.txt
             * img : http://wx1.sinaimg.cn/mw690/0060lm7Tly1fwvecxt5ywj307s08040d.jpg
             * number : 93
             * is_badge : 1
             * type : pingtai
             * level : 1
             */

            private String title;
            private String name;
            private String img;
            private String number;
            private int is_badge;
            private String type;
            private String level;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public int getIs_badge() {
                return is_badge;
            }

            public void setIs_badge(int is_badge) {
                this.is_badge = is_badge;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}