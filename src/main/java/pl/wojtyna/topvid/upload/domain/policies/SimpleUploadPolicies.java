package pl.wojtyna.topvid.upload.domain.policies;

import pl.wojtyna.topvid.upload.domain.UploadPolicy;

class SimpleUploadPolicies implements UploadPolicies {

    @Override
    public UploadPolicy maxSize(int maxSizeInMB) {
        return new MaxSizeUploadPolicy(maxSizeInMB);
    }

    @Override
    public UploadPolicy userUploadLimits() {
        return new UserLimitsUploadPolicy();
    }

    @Override
    public UploadPolicy violentContent() {
        return new ViolentContentUploadPolicy();
    }
}
