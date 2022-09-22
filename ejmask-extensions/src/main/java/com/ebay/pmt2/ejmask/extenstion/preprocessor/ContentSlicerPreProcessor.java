package com.ebay.pmt2.ejmask.extenstion.preprocessor;

import com.ebay.pmt2.ejmask.api.IContentProcessor;
import com.ebay.pmt2.ejmask.api.ProcessorResult;

/**
 * Content size has huge impact on overall masking logic. This preprocessor is
 * to enable resizing of content before actual regex search and replacement
 * starts
 *
 * @author prakv
 */
public class ContentSlicerPreProcessor implements IContentProcessor {

    private static final String NAME = "ejmask.content-slicer";
    private static final int MAX_STRING_LIMIT = 10000;
    private static final int DOC_SIZE = 4000;
    private final int order;
    private final int maxContentSize;
    private final int newSize;

    /**
     * Create new instance of ContentSlicerPreProcessor
     */
    public ContentSlicerPreProcessor() {
        this(DEFAULT_ORDER - 1, MAX_STRING_LIMIT, DOC_SIZE);
    }

    /**
     * Create new instance of ContentSlicerPreProcessor
     *
     * @param priority       as priority in execution cycle
     * @param maxStringLimit as content size limit
     * @param newSize        as new content size
     */
    public ContentSlicerPreProcessor(int priority, int maxStringLimit, int newSize) {
        this.order = priority;
        this.maxContentSize = maxStringLimit;
        this.newSize = newSize;
    }

    /**
     * Return Name of the pre processor for debugging
     *
     * @return Name of preprocessor
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Get the value of priority
     *
     * @return the value of priority
     */
    @Override
    public int getOrder() {
        return this.order;
    }

    /**
     * pre-process the given content if eligible
     *
     * @param content as sting which need to be masked
     * @return preprocessed content if eligible otherwise null
     */
    @Override
    public ProcessorResult preProcess(String content) {
        if (content.length() > this.maxContentSize) {
            return new ProcessorResult(true, content.substring(0, this.newSize));
        }
        return new ProcessorResult(true);
    }
}
