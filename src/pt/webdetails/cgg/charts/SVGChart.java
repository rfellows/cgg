/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.webdetails.cgg.charts;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.transcoder.svg2svg.SVGTranscoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;

/**
 *
 * @author pdpi
 */
public class SVGChart implements Chart {

    private static final Log logger = LogFactory.getLog(SVGChart.class);
    private Document svg;
    private String svgSource;

    public SVGChart(String svgSource) {
        this.svgSource = svgSource;
    }

    public SVGChart(Document doc) {
        this.svg = doc;
    }

    public void toPNG(OutputStream out) {
        PNGTranscoder t = new PNGTranscoder();
        TranscoderInput input = new TranscoderInput(svg);
        TranscoderOutput output = new TranscoderOutput(out);
        try {
            t.transcode(input, output);
            out.flush();
        } catch (IOException ex) {
            logger.error(ex);
        } catch (TranscoderException ex) {
            logger.error(ex);
        }
    }

    public void toSVG(OutputStream out) {
        SVGTranscoder t = new SVGTranscoder();
        TranscoderInput input = new TranscoderInput(svg);
        TranscoderOutput output = new TranscoderOutput(new OutputStreamWriter(out));

        try {
            t.transcode(input, output);
            out.flush();
        } catch (IOException ex) {
            logger.error(ex);
        } catch (TranscoderException ex) {
            logger.error(ex);
        }
    }
}
