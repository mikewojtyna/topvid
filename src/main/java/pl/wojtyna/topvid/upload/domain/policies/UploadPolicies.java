package pl.wojtyna.topvid.upload.domain.policies;

import pl.wojtyna.topvid.patterns.AbstractFactoryPattern;
import pl.wojtyna.topvid.patterns.FactoryMethodPattern;
import pl.wojtyna.topvid.upload.domain.UploadPolicy;

@AbstractFactoryPattern
public interface UploadPolicies {

    UploadPolicy maxSize(int maxSizeInMB);

    UploadPolicy userUploadLimits();

    UploadPolicy violentContent();

    @FactoryMethodPattern
    static UploadPolicies instance() {
        // Some complex instantiation logic might happen here to justify the usage of factory method pattern
        return new SimpleUploadPolicies();
    }
}
