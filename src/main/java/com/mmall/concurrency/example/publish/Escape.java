package com.mmall.concurrency.example.publish;

import com.mmall.concurrency.annoations.NotRecommend;
import com.mmall.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**线程不安全的对象发布
 * 对象逸出
 * @author Administrator
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {

    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    //内部类
    private class InnerClass {

        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
