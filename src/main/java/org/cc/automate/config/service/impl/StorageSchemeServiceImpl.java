package org.cc.automate.config.service.impl;

import org.cc.automate.config.domain.StorageScheme;
import org.cc.automate.config.service.StorageSchemeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("storageSchemeService")
public class StorageSchemeServiceImpl extends ServiceImpl<StorageScheme> implements StorageSchemeService {
}
