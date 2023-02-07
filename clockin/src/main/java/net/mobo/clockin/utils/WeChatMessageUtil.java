package net.mobo.clockin.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeChatMessageUtil implements Serializable {

    public final static String MSG_TYPE_TEXT = "text";
    public final static String MSG_TYPE_MARKDOWN = "markdown";

    private String msgtype;
    private Text text;
    private MarkDown markdown;

    private WeChatMessageUtil(Text text) {
        this.msgtype = MSG_TYPE_TEXT;
        this.text = text;
    }

    private WeChatMessageUtil(MarkDown markdown) {
        this.msgtype = MSG_TYPE_MARKDOWN;
        this.markdown = markdown;
    }

    private static class Text implements Serializable {
        /**
         * 文本内容
         */
        private String content;
        /**
         * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，
         */
        private List<String> mentioned_list;
        /**
         * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
         */
        private List<String> mentioned_mobile_list;

        public String getContent() {

            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentioned_list() {
            return mentioned_list;
        }

        public void setMentioned_list(List<String> mentioned_list) {
            this.mentioned_list = mentioned_list;
        }

        public List<String> getMentioned_mobile_list() {
            return mentioned_mobile_list;
        }

        public void setMentioned_mobile_list(List<String> mentioned_mobile_list) {
            this.mentioned_mobile_list = mentioned_mobile_list;
        }

        public Text(String content, List<String> mentionedList, List<String> mentionedMobileList) {
            this.content = content;
            this.mentioned_list = mentionedList;
            this.mentioned_mobile_list = mentionedMobileList;
        }
    }


    public static class TextBuilder implements Serializable {
        /**
         * 当需要@all时候需要填入mentioned_list或mentioned_mobile_list中的
         */
        private static final String AT_ALL = "@all";
        private String content;
        private List<String> mentionedList;
        private List<String> mentionedMobileList;

        private TextBuilder(String messageType, String content) {
            this.content = messageType + content;
        }

        /**
         * 添加userId，用于在消息中@某人
         *
         * @param mentioned 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addUserIdForAt(String... mentioned) {
            if (mentioned != null && mentioned.length > 0) {
                if (mentionedList == null) {
                    mentionedList = new ArrayList<>();
                }
                mentionedList.addAll(Arrays.asList(mentioned));
            } else {
                addUserIdForAt(AT_ALL);
            }
            return this;
        }

        /**
         * 添加手机号，用于添加某人，没有添加则@所有人
         *
         * @param mobiles 企业微信userId
         * @return 返回建造者本身
         */
        public TextBuilder addMobileForAt(String... mobiles) {
            if (mobiles != null && mobiles.length > 0) {
                if (mentionedMobileList == null) {
                    mentionedMobileList = new ArrayList<>();
                }
                mentionedMobileList.addAll(Arrays.asList(mobiles));
            } else {
                addMobileForAt(AT_ALL);
            }
            return this;
        }

        public WeChatMessageUtil build() {
            return new WeChatMessageUtil(new Text(content, mentionedList, mentionedMobileList));
        }

        public static String getAtAll() {
            return AT_ALL;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getMentionedList() {
            return mentionedList;
        }

        public void setMentionedList(List<String> mentionedList) {
            this.mentionedList = mentionedList;
        }

        public List<String> getMentionedMobileList() {
            return mentionedMobileList;
        }

        public void setMentionedMobileList(List<String> mentionedMobileList) {
            this.mentionedMobileList = mentionedMobileList;
        }
    }


    public static String getMsgtypeText() {
        return MSG_TYPE_TEXT;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }


    public static class MarkDown {
        /**
         * 文本内容
         */
        private String content;


        public String getContent() {

            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }


        public MarkDown(String content) {
            this.content = content;
        }
    }

    /**
     * MarkDown类型消息builder
     */
    public static class MarkDownBuilder implements Serializable {

        private String content;

        private MarkDownBuilder(String messageType, String content) {
            this.content = messageType+ content;
        }

        public WeChatMessageUtil build() {
            return new WeChatMessageUtil(new MarkDown(content));
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

    public MarkDown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(MarkDown markdown) {
        this.markdown = markdown;
    }

    @Override
    public String toString() {
        return "WeChatMessageUtil{" +
                "msgtype='" + msgtype + '\'' +
                ", text=" + text +
                '}';
    }


    public static TextBuilder infoTextBuilder(String messageType, String content) {
        return new TextBuilder(messageType, content);
    }

    public static TextBuilder errTextBuilder(String messageType, String content) {
        return new TextBuilder(messageType, content);
    }

    public static MarkDownBuilder infoMarkDownBuilder(String messageType, String content) {
        return new MarkDownBuilder(messageType, content);
    }

    public static MarkDownBuilder sucMarkDownBuilder(String messageType, String content) {
        content = "<font color="+"\"info\">" + content + "</font>";
        return new MarkDownBuilder(messageType, content);
    }

    public static MarkDownBuilder errMarkDownBuilder(String messageType, String content) {
        content = "<font color="+"\"warning\">" + content + "</font>";
        return new MarkDownBuilder(messageType, content);
    }
}
