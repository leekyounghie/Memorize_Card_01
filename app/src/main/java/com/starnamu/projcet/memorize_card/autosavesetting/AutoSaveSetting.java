package com.starnamu.projcet.memorize_card.autosavesetting;

import java.io.*;

//텍스트 파일에 설정 정보를 저장하는 클래스. 안드로이드의 프레프런스가 너무 느려 새로 만듬
//Ready()를 호출하여 입출력 준비하고 기록할 때는 CommitWrite, 읽기만 했을 때는 EndReady를 호출한다.
public class AutoSaveSetting {
    String mPath;
    StringBuilder mBuf;
    static final String HEADER = "__Text Preference File__\n";

    // 생성자로 저장될 경로 지정
    public AutoSaveSetting(String Path) throws Exception {
        mPath = Path;
        File file = new File(mPath);
        if (file.exists() == false) {//파일이 유무 판단
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(HEADER.getBytes());
            fos.close();
        }
    }

    public void Reset() {// 설정 파일을 삭제한다.
        File file = new File(mPath);
        file.delete();
    }

    public boolean Ready() { // 버퍼에 읽기 쓰기 준비
        try {
            FileInputStream fis = new FileInputStream(mPath);
            int avail = fis.available();
            byte[] data = new byte[avail];
            while (fis.read(data) != -1) {
            }
            fis.close();
            mBuf = new StringBuilder(avail);
            mBuf.append(new String(data));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean CommitWrite() {// 버퍼 내용을 파일에 기록
        File file = new File(mPath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(mBuf.toString().getBytes());
            fos.close();
        } catch (Exception e) {
            return false;
        }
        mBuf = null;
        return true;
    }

    // 버퍼를 해제하고 읽기를 종료한다. 변경한 내용은 모두 취소된다.
    public void EndReady() {
        mBuf = null;
    }

    // name키의 위치를 검색하여 = 다음 위치를 리턴한다. 없으면 -1을 리턴한다.
    // 우연한 중복 방지를 위해 키 이름앞에 __를 붙인다.
    int FindIdx(String name) {
        String key = "__" + name + "=";
        int idx = mBuf.indexOf(key);
        if (idx == -1) {
            return -1;
        } else {
            return idx + key.length();
        }
    }

    // 문자열 키를 기록한다. 이미 있으면 대체한다.
    public void WriteString(String name, String value) {
        int idx = FindIdx(name);
        if (idx == -1) {
            mBuf.append("__");
            mBuf.append(name);
            mBuf.append("=");
            mBuf.append(value);
            mBuf.append("\n");
        } else {
            int end = mBuf.indexOf("\n", idx);
            mBuf.delete(idx, end);
            mBuf.insert(idx, value);
        }
    }

    // 문자열 키를 읽는다. 없으면 디폴트를 리턴한다.
    public String ReadString(String name, String def) {
        int idx = FindIdx(name);
        if (idx == -1) {
            return def;
        } else {
            int end = mBuf.indexOf("\n", idx);
            return mBuf.substring(idx, end);
        }
    }

    // 정수를 읽는다. 일단 문자열 형태로 읽은 후 변환한다.
    public void WriteInt(String name, int value) {
        WriteString(name, Integer.toString(value));
    }

    // 정수를 기록한다. 문자열 형태로 변환하여 기록한다.
    public int ReadInt(String name, int def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }

    public void WriteLong(String name, long value) {
        WriteString(name, Long.toString(value));
    }

    public long ReadLong(String name, long def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Long.parseLong(s);
        } catch (Exception e) {
            return def;
        }
    }

    // 진위값은 true, false가 아닌 1, 0으로 기록한다.
    public void WriteBoolean(String name, boolean value) {
        WriteString(name, value ? "1" : "0");
    }

    public boolean ReadBoolean(String name, boolean def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return s.equals("1") ? true : false;
        } catch (Exception e) {
            return def;
        }
    }

    public void WriteFloat(String name, float value) {
        WriteString(name, Float.toString(value));
    }

    public float ReadFloat(String name, float def) {
        String s = ReadString(name, "__none");
        if (s.equals("__none")) {
            return def;
        }
        try {
            return Float.parseFloat(s);
        } catch (Exception e) {
            return def;
        }
    }

    // 한꺼번에 값을 삽입하기 위해 준비한다. 헤더 작성하고 충분한 버퍼를 할당한다.
    void BulkWriteReady(int length) {
        mBuf = new StringBuilder(length);
        mBuf.append(HEADER);
        mBuf.append("\n");
    }

    // 문자열 형태로 받은 값을 무조건 뒤에 덧붙인다.
    void BulkWrite(String name, String value) {
        mBuf.append("__");
        mBuf.append(name);
        mBuf.append("=");
        mBuf.append(value);
        mBuf.append("\n");
    }

    // 키를 삭제한다.
    void DeleteKey(String name) {
        int idx = FindIdx(name);
        if (idx != -1) {
            int end = mBuf.indexOf("\n", idx);
            mBuf.delete(idx - (name.length() + 3), end + 1);
        }
    }
}
