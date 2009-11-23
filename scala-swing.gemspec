# Generated by jeweler
# DO NOT EDIT THIS FILE DIRECTLY
# Instead, edit Jeweler::Tasks in Rakefile, and run the gemspec command
# -*- encoding: utf-8 -*-

Gem::Specification.new do |s|
  s.name = %q{scala-swing}
  s.version = "2.7.7"

  s.required_rubygems_version = Gem::Requirement.new(">= 0") if s.respond_to? :required_rubygems_version=
  s.authors = ["pending/unknown"]
  s.date = %q{2009-11-22}
  s.description = %q{scala-swing packaged up in a JavaGem}
  s.email = %q{autobuild@javagems.org}
  s.extra_rdoc_files = [
    "LICENSE"
  ]
  s.homepage = %q{http://www.javagems.org/}
  s.rdoc_options = ["--charset=UTF-8"]
  s.require_paths = ["lib"]
  s.rubygems_version = %q{1.3.5}
  s.summary = %q{A JavaGem version of scala-swing - packaged by JavaGems (http://www.javagems.org) - original by LAMP/EPFL (http://lamp.epfl.ch/)}

  if s.respond_to? :specification_version then
    current_version = Gem::Specification::CURRENT_SPECIFICATION_VERSION
    s.specification_version = 3

    if Gem::Version.new(Gem::RubyGemsVersion) >= Gem::Version.new('1.2.0') then
      s.add_development_dependency(%q<scala-library>, ["~> 2.7.7"])
    else
      s.add_dependency(%q<scala-library>, ["~> 2.7.7"])
    end
  else
    s.add_dependency(%q<scala-library>, ["~> 2.7.7"])
  end
end

