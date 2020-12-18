/**
 * JEBML - Java library to read/write EBML/Matroska elements.
 * Copyright (C) 2004 Jory Stone <jebml@jory.info>
 * Based on Javatroska (C) 2002 John Cannon <spyder@matroska.org>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.ebml.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// THEO_ this class does not used in THEO the chages need for the Android 17 level maintenence
// public class FileDataSource implements DataSource, AutoCloseable
public class FileDataSource implements DataSource
{
  RandomAccessFile file = null;
  FileChannel fc = null;

  public FileDataSource(final String filename) throws FileNotFoundException, IOException
  {
    file = new RandomAccessFile(filename, "r");
    fc = file.getChannel();
  }

  public FileDataSource(final String filename, final String mode) throws FileNotFoundException, IOException
  {
    file = new RandomAccessFile(filename, mode);
    fc = file.getChannel();
  }

  @Override
  public byte readByte()
  {
    try
    {
      return file.readByte();
    }
    catch (final IOException ex)
    {
      return 0;
    }
  }

  @Override
  public int read(final ByteBuffer buff)
  {
    try
    {
      return fc.read(buff);
    }
    catch (final IOException ex)
    {
      return 0;
    }
  }

  @Override
  public long skip(final long offset)
  {
    try
    {
      return file.skipBytes((int) offset);
    }
    catch (final IOException ex)
    {
      return 0;
    }
  }

  @Override
  public long length()
  {
    try
    {
      return file.length();
    }
    catch (final IOException ex)
    {
      return -1;
    }
  }

  @Override
  public long getFilePointer()
  {
    try
    {
      return file.getFilePointer();
    }
    catch (final IOException ex)
    {
      return -1;
    }
  }

  @Override
  public boolean isSeekable()
  {
    return true;
  }

  @Override
  public long seek(final long pos)
  {
    try
    {
      file.seek(pos);
      return file.getFilePointer();
    }
    catch (final IOException ex)
    {
      return -1;
    }
  }

  //THEO_ @Override
  public void close() throws IOException
  {
    fc.close();
    file.close();
  }
}
