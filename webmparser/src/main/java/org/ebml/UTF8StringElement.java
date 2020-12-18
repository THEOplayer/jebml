package org.ebml;

import java.nio.charset.Charset;

public class UTF8StringElement extends StringElement
{
  public static final Charset UTF_8 = Charset.forName("UTF-8"); // StandardCharsets.UTF_8

  public UTF8StringElement()
  {
    super(UTF_8);
  }
}
