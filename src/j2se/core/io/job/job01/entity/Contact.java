package j2se.core.io.job.job01.entity;

public class Contact {
    private int id;
    private String realName;
    private String mobile;
    private Group group;

    public Contact() {
        super();
    }

    public Contact(int id, String realName, String mobile, Group group) {
        super();
        this.id = id;
        this.realName = realName;
        this.mobile = mobile;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<contact id=\"%s\">", this.getId()));
        sb.append(String.format("\r\n<name>%s</name>", this.getRealName()));
        sb.append(String.format("\r\n<mobile>%s</mobile>", this.getMobile()));
        String group = String.format("\r\n<group><id>%s</id><name>%s</name></group>",
                this.getGroup().getId(), this.getGroup().getName());
        sb.append(group);
        sb.append("\r\n</contact>");
        return sb.toString();
    }

}
