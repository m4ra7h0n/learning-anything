/**
 * created by xjj on 2023/2/1
 */

/**
 * @author xjj
 * @version 2
 * @since 1
 * <pre>
 *     {@code https://zh.wikipedia.org/wiki/Javadoc}
 * </pre>
 * <pre>
 *     {@code https://reflectoring.io/howto-format-code-snippets-in-javadoc/}
 * </pre>
 */

public class JavaDoc {



    /**
     * 对变量的描述。
     */
    private int debug = 0;

    /**
     * 点对 (x,y) 的水平和垂直距离
     */
    public int x, y;      // 避免这样的做法

    /**
     * 点的水平距离。
     */
    public int a;

    /**
     * 点的垂直距离。
     */
    public int b;

    /**
     * 简单的行描述
     * <p>更长一些的描述</p>
     * @param  variable 描述文本。
     * @return 描述文本
     */
    public int methodName(int variable) {
        return 1;
    }

    /**
     * 验证一步象棋的移动是否可行。
     *
     * <p>使用 {@link #doMove(int theFromFile, int theFromRank, int theToFile, int theToRank)} 方法来移动棋子。
     * <pre>
     * public class JavadocTest {
     *   // indentation and line breaks are kept
     *
     *   &#64;SuppressWarnings
     *   public List&#60;String&#62; generics(){
     *     // '@', '<' and '>'  have to be escaped with HTML codes
     *     // when used in annotations or generics
     *   }
     * }
     * </pre>
     *
     * An annotation <code>&#64;Foo</code>; and a generic List&#60;String&#62;.
     *
     * Using {@code @code} alone, indentation will be lost, but you don't have to
     * escape special characters:
     *
     * {@code An annotation <code>@Foo</code>; and a generic List<String>}.
     *
     * <pre>{@code
     * public class JavadocTest {
     *   // indentation and line breaks are kept
     *
     *  @literal @SuppressWarnings
     *   public List<String> generics(){
     *     // '<' and '>'  are displayed correctly
     *     // '@' CANNOT be escaped with HTML code, though!
     *   }
     * }
     * }</pre>
     *
     * @param theFromFile 被移动棋子的来源行
     * @param theFromRank 被移动棋子的来源列
     * @param theToFile  被移动棋子的目标行
     * @param theToRank  被移动棋子的目标列
     * @return            如果移动是可行的则返回true，否则返回false
     * @since             1.0
     */
    boolean isValidMove(int theFromFile, int theFromRank, int theToFile, int theToRank) {
        // ...body
        return true;
    }

    /**
     * 移动一个棋子。
     *
     * @see java.math.RoundingMode
     * @deprecated now
     */
    @Deprecated
    void doMove(int theFromFile, int theFromRank, int theToFile, int theToRank)  {
        // ...body
    }

    static int i;

    /**
     *
     * @return {@value}
     */
    static int r() {
        return i;
    }
}
