package com.horen.horenbase.utils;

import com.horen.horenbase.bean.d8.NavigationTag;
import com.horen.horenbase.bean.d8.NavigitionBean;

import java.util.ArrayList;
import java.util.List;

public class TagUtils {

    /**
     * 得到tag集合
     */
    public static List<NavigationTag> toTagList(NavigitionBean bean) {
        List<NavigationTag> tags = new ArrayList<>();
        tags.add(bean.getTags().other.setTagName("#"));
        tags.add(bean.getTags().a.setTagName("A"));
        tags.add(bean.getTags().b.setTagName("B"));
        tags.add(bean.getTags().c.setTagName("C"));
        tags.add(bean.getTags().d.setTagName("D"));
        tags.add(bean.getTags().f.setTagName("F"));
        tags.add(bean.getTags().g.setTagName("G"));
        tags.add(bean.getTags().h.setTagName("H"));
        tags.add(bean.getTags().j.setTagName("J"));
        tags.add(bean.getTags().k.setTagName("K"));
        tags.add(bean.getTags().l.setTagName("L"));
        tags.add(bean.getTags().m.setTagName("M"));
        tags.add(bean.getTags().n.setTagName("N"));
        tags.add(bean.getTags().o.setTagName("O"));
        tags.add(bean.getTags().p.setTagName("P"));
        tags.add(bean.getTags().q.setTagName("Q"));
        tags.add(bean.getTags().r.setTagName("R"));
        tags.add(bean.getTags().s.setTagName("S"));
        tags.add(bean.getTags().t.setTagName("T"));
//        tags.add(bean.getTags().u.setTagName("U"));
//        tags.add(bean.getTags().v.setTagName("V"));
        tags.add(bean.getTags().w.setTagName("W"));
        tags.add(bean.getTags().x.setTagName("X"));
        tags.add(bean.getTags().y.setTagName("Y"));
        tags.add(bean.getTags().z.setTagName("Z"));
        return tags;
    }
}
